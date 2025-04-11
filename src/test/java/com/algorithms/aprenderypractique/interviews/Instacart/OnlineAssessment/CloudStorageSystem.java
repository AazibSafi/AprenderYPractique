package com.algorithms.aprenderypractique.interviews.Instacart.OnlineAssessment;

import java.util.*;
import java.util.stream.Collectors;

public class CloudStorageSystem {
    private final HashMap<String, Integer> storage; // File storage (filename -> size)
    private final HashMap<String, String> fileOwners; // File ownership (filename -> userId)
    private final HashMap<String, User> users; // User data (userId -> User object)
    private final HashMap<String, HashMap<String, Integer>> backups; // Backup storage (userId -> backed-up files)

    public CloudStorageSystem() {
        this.storage = new HashMap<>();
        this.fileOwners = new HashMap<>();
        this.users = new HashMap<>();
        this.backups = new HashMap<>();
        // Add an "admin" user with unlimited capacity
        this.users.put("admin", new User(Integer.MAX_VALUE));
    }

    static class User {
        int capacity;       // Total capacity in bytes
        int usedCapacity;   // Used capacity in bytes

        public User(int capacity) {
            this.capacity = capacity;
            this.usedCapacity = 0;
        }

        public int getRemainingCapacity() {
            return capacity - usedCapacity;
        }
    }

    // Add a new user to the system with a storage capacity limit
    public boolean addUser(String userId, int capacity) {
        if (users.containsKey(userId)) {
            return false; // User already exists
        }
        users.put(userId, new User(capacity));
        return true;
    }

    // Get the size of a file if it exists
    public Optional<Integer> getFileSize(String name) {
        return storage.containsKey(name) ? Optional.of(storage.get(name)) : Optional.empty();
    }

    // Delete a file and adjust the used capacity for the user who owns it
    public Optional<Integer> deleteFile(String name) {
        if (!storage.containsKey(name)) {
            return Optional.empty(); // File does not exist
        }
        int fileSize = storage.get(name);
        String owner = fileOwners.get(name);

        // Remove the file and adjust the user's used capacity
        storage.remove(name);
        fileOwners.remove(name);
        users.get(owner).usedCapacity -= fileSize;

        return Optional.of(fileSize);
    }

    // Retrieve the top N largest files with a specific prefix
    public List<String> getNLargest(String prefix, int n) {
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (e1,e2) -> e2.getValue() - e1.getValue());

        for(Map.Entry<String, Integer> entry : storage.entrySet()) {
            if(entry.getKey().startsWith(prefix))
                pq.add(entry);

            if(pq.size() > n)   pq.remove();
        }

        return pq.stream().map(e -> e.getKey()+"("+e.getValue()+")").collect(Collectors.toList());
    }

    // Add a new file to the storage
    public boolean addFile(String name, int size) {
        if(storage.containsKey(name))
            return false;
        storage.put(name, size);
        return true;
    }

    // Add a file owned by a specific user
    public Optional<Integer> addFileBy(String userId, String name, int size) {
        if (!users.containsKey(userId)) {
            return Optional.empty(); // User does not exist
        }
        User user = users.get(userId);
        if (storage.containsKey(name) || user.usedCapacity + size > user.capacity) {
            return Optional.empty(); // File already exists or exceeds user's capacity
        }
        storage.put(name, size);
        fileOwners.put(name, userId);
        user.usedCapacity += size;
        return Optional.of(user.getRemainingCapacity());
    }

    // Merge user2 into user1
    public Optional<Integer> mergeUser(String userId1, String userId2) {
        if (!users.containsKey(userId1) || !users.containsKey(userId2) || userId1.equals(userId2)) {
            return Optional.empty(); // Invalid merge operation
        }
        User user1 = users.get(userId1);
        User user2 = users.get(userId2);

        // Transfer ownership of files
        fileOwners.forEach((fileName, userId) -> {
            if (userId.equals(userId2)) {
                int fileSize = storage.get(fileName);
                fileOwners.put(fileName, userId1);
                user1.usedCapacity += fileSize;
                user2.usedCapacity -= fileSize;
            }
        });

        // Merge capacity
        user1.capacity += user2.capacity;

        // Delete user2 and its backup
        users.remove(userId2);
        backups.remove(userId2);

        return Optional.of(user1.getRemainingCapacity());
    }

    // Backup the current state of all files owned by a user
    public Optional<Integer> backupUser(String userId) {
        if (!users.containsKey(userId)) {
            return Optional.empty(); // User does not exist
        }
        // Collect all files owned by the user
        HashMap<String, Integer> userFiles = new HashMap<>();
        for (Map.Entry<String, String> entry : fileOwners.entrySet()) {
            if (entry.getValue().equals(userId)) {
                userFiles.put(entry.getKey(), storage.get(entry.getKey()));
            }
        }
        backups.put(userId, userFiles); // Overwrite any previous backup
        return Optional.of(userFiles.size()); // Return the number of backed-up files
    }

    // Restore the state of a user's files from the latest backup
    public Optional<Integer> restoreUser(String userId) {
        if (!users.containsKey(userId)) {
            return Optional.empty(); // User does not exist
        }

        HashMap<String, Integer> backup = backups.getOrDefault(userId, new HashMap<>());
        int restoredFilesCount = 0;

        // Remove all current files owned by the user
        Iterator<Map.Entry<String, String>> iterator = fileOwners.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (entry.getValue().equals(userId)) {
                storage.remove(entry.getKey());
                iterator.remove();
            }
        }

        // Restore files from backup
        for (Map.Entry<String, Integer> entry : backup.entrySet()) {
            String fileName = entry.getKey();
            int fileSize = entry.getValue();
            if (!storage.containsKey(fileName)) {
                storage.put(fileName, fileSize);
                fileOwners.put(fileName, userId);
                restoredFilesCount++;
            }
        }

        return Optional.of(restoredFilesCount);
    }

}
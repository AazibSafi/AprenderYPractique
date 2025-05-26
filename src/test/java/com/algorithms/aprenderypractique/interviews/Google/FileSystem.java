package com.algorithms.aprenderypractique.interviews.Google;

import java.util.*;

/**
 * @see com.algorithms.aprenderypractique.Algorithms.Design.DesignFileSystem
 */
public class FileSystem {
    /*
        File System {
            1 : {id: 1, name: "file_1", type: "file", size: 10},
            2 : {id: 2, name: "directory_1", type: "directory", children: {2, 3}},
            3 : {id: 1, name: "file_2", type: "file", size: 10},
        }
     */

    Map<Long, File> fileDirectory;   // {File_ID, File}
    Map<Long, Long> fileParents;    //  {File_ID, Parent File_ID}

    FileSystem() {
        fileDirectory = new HashMap<>();
        fileParents = new HashMap<>();
    }

    int getSize(long id) {
        if(!fileDirectory.containsKey(id))
            return 0;

        File file = fileDirectory.get(id);
        if(Objects.equals(file.type, "file"))
            return file.size;

        return file.children.stream().mapToInt(this::getSize).sum();
    }

    String getPath(long id) {
        if(!fileDirectory.containsKey(id))
            return "";

        String fileName = fileDirectory.get(id).name;
        Long parentId = fileParents.get(id);

        if(!fileParents.containsKey(id))
            return fileName;

        return getPath(parentId) + "/" + fileName;
    }

    static class File {
        long id;
        String name;
        String type;
        int size;
        List<Integer> children;
        File() {
            children = new ArrayList<>();
        }
    }

}

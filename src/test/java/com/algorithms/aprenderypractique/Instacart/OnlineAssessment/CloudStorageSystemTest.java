package com.algorithms.aprenderypractique.Instacart.OnlineAssessment;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class CloudStorageSystemTest extends BaseTest {

    @Test
    public void testNLargest() {
        CloudStorageSystem system = new CloudStorageSystem();

        // Add files
        system.addFile("/dir1/dir2/file1.txt", 10);
        system.addFile("/dir1/dir2/file2.txt", 20);
        system.addFile("/dir1/file3.txt", 5);
        system.addFile("/dir1/dir2/file4.txt", 15);

        // Test getNLargest
        //Assert.assertTrue(CommonHelper.isEquals(Arrays.asList("/dir1/dir2/file4.txt(15)", "/dir1/dir2/file2.txt(20)"), system.getNLargest("/dir1/dir2", 2)));

        System.out.println(system.getNLargest("/dir1/dir2", 2)); // ["/dir1/dir2/file4.txt(15)", "/dir1/dir2/file2.txt(20)"]
        System.out.println(system.getNLargest("/dir1/dir2", 5)); // ["/dir1/dir2/file2.txt(20)", "/dir1/dir2/file4.txt(15)", "/dir1/dir2/file1.txt(10)"]
        System.out.println(system.getNLargest("/non-existing-prefix", 3)); // []
    }

    @Test
    public void test() {
        CloudStorageSystem system = new CloudStorageSystem();

        // Add users
        Assert.assertTrue(system.addUser("user1", 100));
        Assert.assertTrue(system.addUser("user2", 200));

        // Add files by users
        Assert.assertEquals(Optional.of(50), system.addFileBy("user1", "/file1.txt", 50)); // Optional[50]
        Assert.assertEquals(Optional.of(10), system.addFileBy("user1", "/file2.txt", 40)); // Optional[10]
        Assert.assertEquals(Optional.of(50), system.addFileBy("user2", "/file3.txt", 150)); // Optional[50]

        // Backup user1
        Assert.assertEquals(Optional.of(2), system.backupUser("user1")); // Optional[2]

        // Add more files and change state
        Assert.assertEquals(Optional.empty(), system.addFileBy("user1", "/file4.txt", 30)); // Optional.empty (exceeds capacity)
        Assert.assertEquals(Optional.of(0), system.addFileBy("user2", "/file5.txt", 50)); // Optional[0]

        // Restore user1
        Assert.assertEquals(Optional.of(2), system.restoreUser("user1")); // Optional[2] (restores /file1.txt and /file2.txt)

        // Backup and restore non-existent user
        Assert.assertEquals(Optional.empty(), system.backupUser("user3")); // Optional.empty
        Assert.assertEquals(Optional.empty(), system.restoreUser("user3")); // Optional.empty

        // Merge user2 into user1
        Assert.assertEquals(Optional.of(10), system.mergeUser("user1", "user2")); // Optional[260] (100 + 200 - 90 used)

        // Restore user1
        Assert.assertEquals(Optional.of(2), system.restoreUser("user1")); // Optional[2] (restores /file1.txt and /file2.txt)

        // Check user2 backup removal
        Assert.assertEquals(Optional.empty(), system.restoreUser("user2")); // Optional.empty
    }

}

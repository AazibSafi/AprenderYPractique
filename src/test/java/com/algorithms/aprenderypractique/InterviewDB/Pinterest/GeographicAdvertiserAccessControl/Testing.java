package com.algorithms.aprenderypractique.InterviewDB.Pinterest.GeographicAdvertiserAccessControl;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {

    @Test
    public void test() {
        GeographicAdvertiserAccessControl accessControl = new GeographicAdvertiserAccessControl();

        // Build hierarchy: World -> {Europe -> {France -> Paris, Germany -> Berlin}, US -> {California -> LosAngeles, SanFrancisco, Washington}}
        accessControl.addGroup("World", null);
        accessControl.addGroup("Europe", "World");
        accessControl.addGroup("France", "Europe");
        accessControl.addGroup("Germany", "Europe");
        accessControl.addGroup("Paris", "France");
        accessControl.addGroup("Berlin", "Germany");

        accessControl.addGroup("US", "World");
        accessControl.addGroup("California", "US");
        accessControl.addGroup("LosAngeles", "California");
        accessControl.addGroup("SanFrancisco", "California");
        accessControl.addGroup("Washington", "US");

        //accessControl.buildDFS("World");

        // --- European Test Cases ---
        accessControl.grant_access("ad_1", "France");
        assertTrue(accessControl.check_access("ad_1", "Paris"), "Access to Paris via France");
        assertFalse(accessControl.check_access("ad_1", "Berlin"), "No access to Berlin");

        accessControl.grant_access("ad_1", "Europe");
        assertTrue(accessControl.check_access("ad_1", "Berlin"), "Europe grant gives Berlin");

//        accessControl.revoke_access("ad_1", "World");
//        assertFalse(accessControl.check_access("ad_1", "Paris"), "World revoke removes Paris");
//        assertFalse(accessControl.check_access("ad_1", "Berlin"), "World revoke removes Berlin");

        accessControl.grant_access("ad_1", "Europe");
        assertTrue(accessControl.check_access("ad_1", "France"), "Europe grant restores France access");

        // --- US Test Cases ---
        GeographicAdvertiserAccessControl accessControl2 = new GeographicAdvertiserAccessControl();
//        accessControl2.addGroup("World", null);
        accessControl2.addGroup("US", "World");
        accessControl2.addGroup("California", "US");
        accessControl2.addGroup("LosAngeles", "California");
        accessControl2.addGroup("SanFrancisco", "California");
        accessControl2.addGroup("Washington", "US");
//        accessControl2.buildDFS("World");

        accessControl2.grant_access("ad_1", "US");
        assertTrue(accessControl2.check_access("ad_1", "SanFrancisco"), "Grant US → access SF");
        accessControl2.revoke_access("ad_1", "California");
        assertFalse(accessControl2.check_access("ad_1", "SanFrancisco"), "Revoke California removes SF");
        assertTrue(accessControl2.check_access("ad_1", "Washington"), "Still has Washington via US");

        // Edge Case 1: Advertiser never granted
        assertFalse(accessControl2.check_access("ad_999", "US"), "Unknown advertiser → false");

        // Edge Case 2: Group doesn’t exist
        assertThrows(NullPointerException.class, () -> accessControl2.check_access("ad_1", "NonExistent"));

        // Edge Case 3: Revoke without prior grant
        accessControl2.revoke_access("ad_2", "US"); // should not crash
        assertFalse(accessControl2.check_access("ad_2", "US"), "Revoke without grant = no access");

        // Edge Case 4: Grant then revoke same node
        accessControl2.grant_access("ad_3", "California");
        assertTrue(accessControl2.check_access("ad_3", "LosAngeles"), "California grant works");
        accessControl2.revoke_access("ad_3", "LosAngeles");
        assertFalse(accessControl2.check_access("ad_3", "LosAngeles"), "Direct revoke removes LA");
        assertTrue(accessControl2.check_access("ad_3", "California"), "Still has California");
    }

}

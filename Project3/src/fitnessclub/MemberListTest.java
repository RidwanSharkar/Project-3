package fitnessclub;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemberListTest {

    /* add() Testing for each member type */
    @Test
    public void testAddValidBasicMember() {
        MemberList memberList = new MemberList();
        Profile profile = new Profile("David", "Smith", new Date(12, 12, 2000));
        Member basicMember = new Basic(profile, new Date(2, 23, 2024), Location.BRIDGEWATER);
        assertTrue("Adding a valid Basic member should return True", memberList.add(basicMember));
    }

    @Test
    public void testAddDuplicateBasicMember() {
        MemberList memberList = new MemberList();
        Profile profile = new Profile("John", "Smith", new Date(11, 11, 2000));
        Member basicMember = new Basic(profile, new Date(2, 23, 2024), Location.BRIDGEWATER);
        memberList.add(basicMember);
        assertFalse("Adding a duplicate Basic member should return False", memberList.add(basicMember));
    }

    @Test
    public void testAddValidFamilyMember() {
        MemberList memberList = new MemberList();
        Profile profile = new Profile("Bob", "Smith", new Date(10, 10, 2000));
        Member familyMember = new Family(profile, new Date(2, 23, 2024), Location.EDISON);
        assertTrue("Adding a valid Family member should return True", memberList.add(familyMember));
    }

    @Test
    public void testAddDuplicateFamilyMember() {
        MemberList memberList = new MemberList();
        Profile profile = new Profile("Jane", "Smith", new Date(9, 9, 2000));
        Member familyMember = new Family(profile, new Date(2, 23, 2024), Location.EDISON);
        memberList.add(familyMember);
        assertFalse("Adding a duplicate Family member should return False", memberList.add(familyMember));
    }

    @Test
    public void testAddValidPremiumMember() {
        MemberList memberList = new MemberList();
        Profile profile = new Profile("Sarah", "Smith", new Date(8, 8, 2000));
        Member premiumMember = new Premium(profile, new Date(2, 23, 2024), Location.FRANKLIN);
        assertTrue("Adding a valid Premium member should return True", memberList.add(premiumMember));
    }

    @Test
    public void testAddDuplicatePremiumMember() {
        MemberList memberList = new MemberList();
        Profile profile = new Profile("Megan", "Smith", new Date(7, 7, 2000));
        Member premiumMember = new Premium(profile, new Date(2, 23, 2024), Location.FRANKLIN);
        memberList.add(premiumMember);
        assertFalse("Adding a duplicate Premium member should return False", memberList.add(premiumMember));
    }

    /* remove() Testing */
    @Test
    public void testRemoveExistingMember() {
        MemberList memberList = new MemberList();
        Profile profile = new Profile("Craig", "Smith", new Date(6, 6, 2000));
        Member premiumMember = new Premium(profile, new Date(2, 23, 2024), Location.FRANKLIN);
        memberList.add(premiumMember);
        assertTrue("Removing this existing member should return True", memberList.remove(premiumMember));
    }

    @Test
    public void testRemoveNonExistentMember() {
        MemberList memberList = new MemberList();
        Profile profile = new Profile("Bill", "Smith", new Date(5, 5, 2000));
        Member basicMember = new Basic(profile, new Date(2, 23, 2024), Location.PISCATAWAY);
        assertFalse("Removing this non-existent member should return False", memberList.remove(basicMember));
    }
}
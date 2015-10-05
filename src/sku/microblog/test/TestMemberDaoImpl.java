package sku.microblog.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sku.microblog.business.domain.Member;
import sku.microblog.business.service.MemberDao;
import sku.microblog.dataaccess.MemberDaoImpl;

public class TestMemberDaoImpl {
	private MemberDao memberDao;
	private Member testMember;
	
	@Before
	public void init() {
		memberDao = new MemberDaoImpl();
		testMember = new Member("testing@testing.com", "testing member", "test123"); 
	}
	
	@Test
	public void testInsertMember() {
		Member newMember = new Member("first_user@kitsch.com", "first_user", "Abc123");
		memberDao.insertMember(newMember);
		
		Member[] allMembers = memberDao.selectAllMembers();
		for (Member member : allMembers) {
			System.out.println(member.toString());
		}
		
		memberDao.deleteMember(newMember);
	}

	@Test
	public void testSelectMember() {
		memberDao.insertMember(testMember);
		Member selectedMember = memberDao.selectMemberAsEmail("testing@testing.com");
		System.out.println(selectedMember.toString());
		selectedMember = memberDao.selectMemberAsName("testing member");
		System.out.println(selectedMember.toString());
		Member[] selectedMembers = memberDao.selectMemberAsRole(Member.ADMINISTRATOR);
		for (Member member : selectedMembers) {
			System.out.print("===== ADMINISTRATORS : ");
			System.out.println(member.toString());
		}
		selectedMembers = memberDao.selectMemberAsRole(Member.NORMAL_USER);
		for (Member member : selectedMembers) {
			System.out.print("===== NORMAL USERS : ");
			System.out.println(member.toString());
		}
		memberDao.deleteMember(testMember);
	}
	
	@Test
	public void testUpdateMember() {
		memberDao.insertMember(testMember);
		Member tempMember = memberDao.selectMemberAsName(testMember.getName());
		tempMember.setPassword("1234");
		tempMember.setRole(Member.SUPER_USER);
		memberDao.updateMember(tempMember);
		System.out.println(memberDao.memberNameExists(tempMember.getName()));
		memberDao.deleteMember(testMember);
	}
	
	@Test
	public void test() {
		
	}
	/*@After
	public void end() {
		Member[] allMembers = memberDao.selectAllMembers();
		for (Member member : allMembers) {
			System.out.println(member.toString());
		}
	}*/
}

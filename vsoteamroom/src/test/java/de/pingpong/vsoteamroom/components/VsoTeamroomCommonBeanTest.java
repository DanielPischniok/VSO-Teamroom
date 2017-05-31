package de.pingpong.vsoteamroom.components;

import org.junit.Test;

public class VsoTeamroomCommonBeanTest {
	
	VsoTeamroomCommonBean commonBean;
	
	@Test
	public void testHashValue(){
		commonBean = new VsoTeamroomCommonBean();
		String testPassword = "hallo";
		
		String base64Hash = commonBean.hashPassword(testPassword);
		
		System.out.println(base64Hash);
	}

}

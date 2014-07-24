package com.varian.rest;

import javax.naming.ldap.LdapContext;

public class mainfile {
	public static void main(String[] args) {
	try{
	    LdapContext ctx = ActiveDirectory.getConnection("vdandeka", "Vai@1234");
	    ctx.close();
	}
	catch(Exception e){
	    //Failed to authenticate user!
	    e.printStackTrace();
	}
	}

}

package com.varian.rest;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import static javax.naming.directory.SearchControls.SUBTREE_SCOPE;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.InitialLdapContext;
 
//Imports for changing password
import javax.naming.directory.ModificationItem;
import javax.naming.directory.BasicAttribute;
import javax.naming.ldap.StartTlsResponse;
import javax.naming.ldap.StartTlsRequest;
import javax.net.ssl.*;
 
//******************************************************************************
//**  ActiveDirectory
//*****************************************************************************/
/**
 *   Provides static methods to authenticate users, change passwords, etc. 
 *
 ******************************************************************************/
 
public class ActiveDirectory {
   
    private ActiveDirectory(){}
 
 
  //**************************************************************************
  //** getConnection
  //*************************************************************************/
  /**  Used to authenticate a user given a username/password. Domain name is
   *   derived from the fully qualified domain name of the host machine.
   */
    public static LdapContext getConnection(String username, String password) throws NamingException {
        return getConnection(username, password,null,null);
    }
    public static LdapContext getConnection(String username, String password, String domainName) throws NamingException {
        return getConnection(username, password, domainName,null);
    }
    public static LdapContext getConnection(String username, String password, String domainName, String serverName) throws NamingException {
    	 //vms.ad.varian.com
        if (domainName==null){
            try{
                String fqdn = java.net.InetAddress.getLocalHost().getCanonicalHostName();
                if (fqdn.split("\\.").length>1) domainName = fqdn.substring(fqdn.indexOf(".")+1);
            }
            catch(java.net.UnknownHostException e){}
        }
         
  //      System.out.println("Authenticating " + username + "@" + domainName + " through " + serverName);
 
        if (password!=null){
            password = password.trim();
            if (password.length()==0) password = null;
        }
 
        //bind by using the specified username/password
        Hashtable props = new Hashtable();
        String principalName = username + "@" + domainName;
        props.put(Context.SECURITY_PRINCIPAL, principalName);
        if (password!=null) props.put(Context.SECURITY_CREDENTIALS, password);
 
 
        String ldapURL = "ldap://" + ((serverName==null)? domainName : serverName + "." + domainName) + '/';
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, ldapURL);
        try{
            return new InitialLdapContext(props, null);
        }
        catch(javax.naming.CommunicationException e){
            throw new NamingException("Failed to connect to " + domainName + ((serverName==null)? "" : " through " + serverName));
        }
        catch(NamingException e){
            throw new NamingException("Failed to authenticate " + username + "@" + domainName + ((serverName==null)? "" : " through " + serverName));
        }
    }
 
  //**************************************************************************
  //** getConnection
  //*************************************************************************/
  /**  Used to authenticate a user given a username/password and domain name.
   */

}

package chapter5;

import java.net.URI;
import java.net.URISyntaxException;

public class URISplitter {
    public static void main(String args[]){
        for(int i = 0; i < args.length; i++){
            try{
                URI u = new URI(args[i]);
                System.out.println("The URI is" + u);
                if(u.isOpaque()){
                    System.out.println("this is an opaque uri");
                    System.out.println("the scheme is " + u.getScheme());
                    System.out.println("the scheme specific part is " + u.getSchemeSpecificPart());
                    System.out.println("the fragment id is " + u.getFragment());
                }else{
                    System.out.println("this is a hiararchical uri.");
                    System.out.println("the scheme is " + u.getScheme());
                    try{
                        u = u.parseServerAuthority();
                        System.out.println("the host is " + u.getHost());
                        System.out.println("the user info is" + u.getUserInfo());
                        System.out.println("the port is" + u.getPort());
                    }catch(URISyntaxException ex){
                        System.out.println("the authority is " + u.getAuthority());
                    }
                    System.out.println("the path is " + u.getPath());
                    System.out.println("the query string is " + u.getQuery());
                    System.out.println("the fragment id is " + u.getFragment());
                }
            }catch(URISyntaxException ex){
                System.err.println(args[i] + " does not seem to be a URI");
            }
            System.out.println();
        }
    }
}

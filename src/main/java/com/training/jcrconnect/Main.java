package com.training.jcrconnect;

import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.*;
import java.io.File;

public class Main {

    private static final String REP_DEFAULT_DIRECTORY = "d:/teach_practice/resource";

    public static void main(String[] args) {

        Repository repository = new TransientRepository(getRepositoryDir());
        Session session = null;
        try {
            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));

            Node node = session.getRootNode();

            if (! node.hasNode("content")) {
                node.addNode("content");
            }
            session.save();

            NodeIterator iterator = node.getNodes();

            while (iterator.hasNext()) {
                print(iterator.nextNode().getPath());
            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isLive()) session.logout();
        }
    }

    private static File getRepositoryDir() {
        File file = new File(REP_DEFAULT_DIRECTORY);

        if (!file.exists()) file.mkdir();

        return file;
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}

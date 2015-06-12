package org.dstadler.jgit;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;


public class ListTags {
    public static void main(String[] args) throws IOException, GitAPIException {
        // first create a test-repository, the return is including the .get directory here!
        File repoDir = new File("path_to_repo");// createSampleGitRepo();
        
        // now open the resulting repository with a FileRepositoryBuilder
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(repoDir)
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();

        System.out.println("Having repository: " + repository.getDirectory());

      
        
        // the Ref holds an ObjectId for any type of object (tree, commit, blob, tree)
        Ref head = repository.getRef("refs/heads/master");
        System.out.println("Ref of refs/heads/master: " + head);

//        printTags(reposito    ry);
        checkoutTag(repository,"build-1.0.179");
        repository.close();
    }

    private static void printTags(Repository repository) throws GitAPIException {
        List<Ref> call = new Git(repository).tagList().call();
        for (Ref ref : call) {
            System.out.println("Tag: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }
    }
    
    private static void checkoutTag(Repository repository, String tagName) throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException{
        Git git = new Git(repository);
        git.checkout().setName("master").call();
    }

   

}

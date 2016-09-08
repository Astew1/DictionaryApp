import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Alex on 12/31/15.
 */
public class DictionaryTrie {
    private node rootnode;


    public DictionaryTrie(String wordlist){
        File dictionary = new File(wordlist);
        try (BufferedReader br = new BufferedReader(new FileReader(dictionary))) {
            String line;

            while ((line = br.readLine()) != null) {
                insert(line);
            }
        }
        catch(Exception e){}
    }

    private class node{         //other methods rely on the fact that if a node exists then it will have a value that is NOT null
        public char value;
        public boolean end;
        public node next, child;

        public node(char value, boolean end){
            this.value = value;
            this.end = end;
            this.next = null;
            this.child = null;

        }

    }

    public void insert(String s){
        int index = 0;
        if(s.length()==0) {
            System.out.println("Trie Insert -> Argument length of 0");
            return;
        }
        node prevnode = null;
        node currentnode = null;
        if(rootnode!=null){
            prevnode = null;
            currentnode = rootnode;
        }
        if(rootnode==null){
            rootnode = new node(s.charAt(index), false);
            if(s.length() ==1) {
                rootnode.end = true;
                return;
            }
            index++;
            prevnode = rootnode;
            currentnode = rootnode.child;
        }



        while(index<s.length()){
            char c = s.charAt(index);
            if(currentnode==null){              //A new, only child with an alive parent
                currentnode = new node(c, false);
                prevnode.child = currentnode;
            }
            else {
                while (currentnode.value != c) {      //find the current gen with the same letter, else create it
                    if (currentnode.next == null) {
                        currentnode.next = new node(c, false);
                    }
//                    if (index == s.length()) {
//                        currentnode.end = true;
//                        return;
//                    }
                    prevnode = currentnode;
                    currentnode = currentnode.next;
                }                                   //at this point currentnode is the node in the current level with the correct value

            }
            prevnode = currentnode;
            currentnode = currentnode.child;
            index++;

        }
        prevnode.end = true;
    }

    public boolean search(String s){

        if(rootnode==null)
            return false;
        int index = 0;
        node currentnode = rootnode;
        while(index<s.length()) {
            char c = s.charAt(index);
            if (currentnode == null)
                return false;
            while (currentnode.value != c) {
                if (currentnode.next == null)
                    return false;
                currentnode = currentnode.next;
            }
            if (index == s.length() - 1 && currentnode.end)
                return true;
            currentnode = currentnode.child;
            index++;
        }
        return false;
    }

    public void remove(String s) {

    }

    public ArrayList<String> suggestCompletions(String s){      //BUG is that it cannot find a word that is passed in as s, but is not part of any other words, like "hello"
        ArrayList<String> found = new ArrayList<String>();      //THIS BUG appears to be fixed, but should make it so that it deletes possible duplicates
        if(s.length()<2) {
            found.add("String too short to search for completions");
            return found;
        }if(search(s.toLowerCase()))
            found.add(s);


        if(rootnode==null)
            return null;
        //get the node that the search corresponds to - the end char of the started string, and then search it for all the words after it
        node cn = rootnode;
        for (int i = 0; i < s.length(); i++) {
            if(cn.value==s.charAt(i)) {     //found it!
                if (cn.child != null) {
                    cn = cn.child;
                    continue;
                }
                else if(cn.child==null && i<s.length()-1){                      //if the child is
                    return null;
                }
            }
            if(cn.next!=null) { //keep going
                cn = cn.next;
                i-=1;
                continue;
            }
            else{           //a.k.a. we haven't found it AND there are no more
                return found;
            }

        }
        return addwords(cn, found, s);

    }
    public ArrayList<String> addwords(node n, ArrayList<String> list, String wordSoFar){

        if(n==null)
            return list;
        node n1 = n;
        ArrayList<String> newWords = new ArrayList<String>();
        do{
            if (n1.end) {
                newWords.add(wordSoFar + n1.value);
            }
            n1 = n1.next;
        } while (n1 != null);


        do{
            if(n.child!=null)
                addwords(n.child, list, wordSoFar+n.value);
            n = n.next;
        } while (n!=null);
        list.addAll(newWords);
        return list;
    }

    public void printList(ArrayList<String> list){
        if(list==null) {
            System.out.println("No Words Match");
            return;
        }
        for (String s:list){
            System.out.println(s);
        }
    }



}

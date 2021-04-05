import java.util.HashMap;

class Node {

    String data; 
    Node next;

    Node() { 
        this.next = null;
    }

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {

    Node head;
    int listSize = 0;
    HashMap<Character, Node> letterMap = new HashMap<Character, Node>();

    Node makeNode(String data) {

        Node node = new Node(data);
        return node;
    }

    Boolean compareCharacters(String newName, String currName) {

        // get size of shortest name
        int len = ((newName.length() < currName.length()) ? newName.length() : currName.length());

        char newC, currC;
        
        for(int i = 0; i < len; i++) {
            
            newC = newName.charAt(i);
            currC = currName.charAt(i);

            if(newC > currC) {
                return false;

            } else if(newC < currC) {
                return true;

            } else if(i+1 == len) {

                if(newName.length() > currName.length()) {
                    return false;
                }
            }
        }
        return true;
    }

    void checkAndUpdateLetterMap(Node temp, Node curr) {

        char temp_letter = temp.data.charAt(0);
        // update letterMap with new node address if the original address was the first of its kind (letter wise)
        if(curr != null) {

            char curr_letter = curr.data.charAt(0);

            if(curr_letter == temp_letter) {

                if(letterMap.containsKey(curr_letter)) {

                    // if the previous name was already stored in the map
                    if(letterMap.get(curr_letter) == curr) {
                        letterMap.put(curr_letter, temp);
                    } 
                } 
            }
            // if the letter before curr letter is different, and if map doesn't contain letter, add it
            else if(temp_letter != curr_letter) { 
                // and if temp letter doesn't exist, then add it
                if(!letterMap.containsKey(temp_letter)) {
                    letterMap.put(temp_letter, temp);
                }
            }

        } else { // else character is at end of list. 
            // if key/letter exists
            if(letterMap.containsKey(temp_letter)) {

                // compare key value with new value
                boolean b = this.compareCharacters(temp.data, letterMap.get(temp_letter).data);
                if(b) {
                    letterMap.put(temp_letter, temp);
                }
            } else {
                letterMap.put(temp_letter, temp);
            }
        }
    }

    void insertInOrder(String name) {

        Node prev, curr, temp = new Node();
    
        // let head = new node if list is empty
        if(this.head == null) { 
            Node node = makeNode(name);
            this.head = node;

            // update Lettermap. since only node in map, 2nd parameter = null
            this.checkAndUpdateLetterMap(this.head, null);
        }
 
        // compare head name with new name. if new name < old name, let head = new name
        else if(compareCharacters(name, this.head.data))  {

            temp = makeNode(name);
            temp.next = this.head;
            this.head = temp;

           // update letterMap if necessary
            this.checkAndUpdateLetterMap(this.head, temp.next);
        }

        else {
            prev = this.head;
            curr = prev.next;
            boolean searching = true;
            boolean isLess;

            while(searching) {

                isLess = this.compareCharacters(name, curr.data);

                // if new name is less than curr name, store new name here
                if(isLess) {

                    // swap nodes
                    temp = makeNode(name);
                    prev.next = temp;
                    temp.next = curr;
                    searching = false;

                    this.checkAndUpdateLetterMap(temp, curr);

                } else { // if new name is greater than or equal to, compare next name with new name

                    // if next is null then were at the end element. therefore add name to end
                    if(curr.next == null) {

                        curr.next = makeNode(name);
                        searching = false;
                        this.checkAndUpdateLetterMap(curr.next, null);
                    } 

                    else {
                        prev = curr;
                        curr = curr.next;
                    }
                }
            }
        }
        listSize++;
    }


    // be sure to update letterMap if the node youre deleting is in the map. 
    void deleteAt(String name) {
        
        Node curr, prev;
        curr = this.head;
        prev = curr;

        char letter = name.charAt(0);
        boolean searching = true;

        while(searching) {

            if(curr.data.equals(name)) {

                // check if name is the first of its Letter type. if so delete value associated with the key in letterMap
                if(letterMap.containsKey(letter)) {

                    // check if value of that key is the node that will get deleted
                    if(letterMap.get(letter) == curr) {

                        // store next name address into map. ie. replace one being deleted
                        if(curr.next != null) {
                            letterMap.put(letter, curr.next);
                        } else {
                        // delete from map since there are no other names that start with the letter
                        letterMap.put(letter, null);
                        }
                    }
                }

                // remove pointers from curr
                prev.next = curr.next;
                curr = null;
                searching = false;
                
                // update listSize
                listSize--;
            } 
            // if at end of list, cannot find node. 
            else if(curr.next == null) {
                searching = false;

            } else {
                prev = curr; 
                curr = curr.next; 
            }
        }
    }


    int getSize() {

        return listSize;
    }


    // get the count of names starting with a given letter
    int countNamesWithTheLetter(char letter) {

        Node curr = letterMap.get(letter);
        int count = 0; 

        while(curr.data.charAt(0) == letter) {

            curr = curr.next;
            count++;
        }
        return count;
    }


    // prints and retuns number of names that starts with the given letter
    void printNamesWithTheLetter(char letter) {

        boolean notNull = true;
        Node curr = letterMap.get(letter);

        while(notNull && curr.data.charAt(0) == letter) {

            if(curr.next == null) {
                notNull = false;
            } 
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }


    void printList() {

        Node node = this.head;
        // while theres another node, continue
        while(node != null) {

            System.out.print(node.data + " ");

            if(node.next != null) {
                if(node.data.charAt(0) != node.next.data.charAt(0)) {

                    System.out.println("");
                }
            }
            node = node.next;
        }
        System.out.println("\n");
    }

    // print letter map
    void printMap() {

        for(HashMap.Entry<Character, Node>e: letterMap.entrySet()) {

            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}



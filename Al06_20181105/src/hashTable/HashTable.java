package hashTable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HashTable {
	File file = new File("C:/Users/Administrator/Desktop/Result1.txt");
    BufferedWriter bw = null;
    PrintWriter pw = null;
	// 기본 배열의 크기
	private final int DEFAULT_CAPACITY = 59;
	private final int DEFAULT_CAPACITY_2 = 31;
	private Node[] array;
	private int count;

	public HashTable() {
		array = new Node[DEFAULT_CAPACITY];
	}

	static class Node {
		int key; // Node 안의 키
		int value; // Node 안의 키가 가르키는 값

		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}


	}

	// 키에 대한 hash 값을 구하는 메소드
	// 단순히 키를 문자열로 바꾼후 해당 문자의 아스키(ASCII) 값의 합을 이용하는 해시 함수 사용
	// 배열의 크기를 넘지 않기 위해 나머지 연산(Modular)을 사용

	public int hash(int value) {

		return value % DEFAULT_CAPACITY;
	}


	// -------------------------------선형조사-------------------------------//
	public void linear_insert(int value) throws IOException {
		int key = hash(value);
		while (array[key] != null) {
			System.out.print("-->["+key+"]");
			key = (key + 1) % DEFAULT_CAPACITY;
			count++;
		}

		array[key] = new Node(key, value);
		System.out.println("-->["+key+"]");

	}

	// -------------------------------이차원조사-------------------------------//
	public void quadratic_insert(int value) {
		int key = hash(value);
		int i=0;
		while (array[key] != null) {
			System.out.print("-->["+key+"]");
			i++;
			key = (key + (i*i)) % DEFAULT_CAPACITY;
			
			count++;
		}

		array[key] = new Node(key, value);
		System.out.println("-->["+key+"]");

	}

	// -------------------------------더블해싱-------------------------------//
	public void double_insert(int value) {
		int key = hash(value);
		int i=0;
		while (array[key] != null) {
			System.out.print("-->["+key+"]");
			i++;
			key = ((key%DEFAULT_CAPACITY)+i*(key%DEFAULT_CAPACITY_2)) % DEFAULT_CAPACITY;
			
			count++;
		}

		array[key] = new Node(key, value);
		System.out.println("-->["+key+"]");

	}

	public int linear_search(int key) {
		int i = hash(key);
		while (array[i] != null) {
			if (array[i].equals(key))
				return array[i].getValue();
			i = (i + 1) % DEFAULT_CAPACITY;
		}
		return 0;

	}

	//해당 키 값이 있는지 확인
	public boolean contains(int hash, int key) {
		for (int i = 0; i < DEFAULT_CAPACITY; i++) {
			if (array[hash].equals(key)) {
				return true;
			}else {
				return false;
			}
		}

		return false;
	}

	public boolean remove(int key) {
		return linear_delete(hash(key), key);
	}

	public boolean linear_delete(int hash, int key) {
		return false;
		
	}
	
	public void deleteFile() {
		if( file.exists() ){
            file.delete();
        }
	}

	// 배열 요소를 출력하기 위한 메소드
	void print() {
		for (int i = 0; i < array.length; ++i)
			System.out.println(array[i] == null ? "----"+"["+(i+1)+"]" : array[i].getValue()+" "+"["+(i+1)+"]");
		System.out.println("***COUNT***:"+count);
		System.out.println("--------------------------------------------------------------------");
	}

	void Printcount() {
		System.out.println("***COUNT***:"+count);
	}
	public static void main(String[] args) throws IOException {

		HashTable linearHt = new HashTable();
		HashTable quadraticHt = new HashTable();
		HashTable doubleHt = new HashTable();
		Node node;

		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/Data1.txt"));

		for (int i = 0; i < 24; i++) {
			String line = br.readLine();
			if (line == null) {
				break;
			} else {
				System.out.print(line);
				//linearHt.linear_insert(Integer.parseInt(line));
				//quadraticHt.quadratic_insert(Integer.parseInt(line));
				doubleHt.double_insert(Integer.parseInt(line));
			}
		}
		doubleHt.Printcount();
		//ht.print();

	}


}

package hashTable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

	public int getCount() {
		return count;
	}

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

	public int hash(int value) {
		return value % DEFAULT_CAPACITY;
	}

	// -------------------------------선형조사-------------------------------//
	public void linear_insert(int value) throws IOException {
		int key = hash(value);
		while (array[key] != null) {
			System.out.print("-->[" + key + "]");
			key = (key + 1) % DEFAULT_CAPACITY;
			count++;
		}

		array[key] = new Node(key, value);
		System.out.println("-->[" + key + "]");

	}

	// -------------------------------이차원조사-------------------------------//
	public void quadratic_insert(int value) {
		int key = hash(value);
		int i = 0;
		while (array[key] != null) {
			System.out.print("-->[" + key + "]");
			i++;
			key = (key + (i * i)) % DEFAULT_CAPACITY;

			count++;
		}

		array[key] = new Node(key, value);
		System.out.println("-->[" + key + "]");

	}

	// -------------------------------더블해싱-------------------------------//
	public void double_insert(int value) {
		int key = hash(value);
		int i = 0;
		while (array[key] != null ) {
			System.out.print("-->[" + key + "]");
			i++;
			key++; // 충돌방지
			key = ((key % DEFAULT_CAPACITY) + i * (key % DEFAULT_CAPACITY_2)) % DEFAULT_CAPACITY;

			count++;
		}

		array[key] = new Node(key, value);
		System.out.println("-->[" + key + "]");

	}

	public boolean linear_search(int value) throws IOException {
		int key = hash(value);
		while (array[key] != null) {
			if (array[key].getValue() == value && array[key].getValue() != -1) {
				bw = new BufferedWriter(new FileWriter(file, true));
				pw = new PrintWriter(bw, true);
				if (file.isFile() && file.canWrite()) {
					// 쓰기
					pw.println(array[key].getValue() + "[" + key + "]");
				}
				bw.close();
				System.out.println(array[key].getValue() + "[" + key + "]");
				return true;
			} else {
				key = (key + 1) % DEFAULT_CAPACITY;
			}

		}
		return false;
	}

	public boolean linear_delete(int value) {
		int key = hash(value);
		while (array[key] != null) {
			System.out.print("-->[" + key + "]");
			if (array[key].getValue() == value && array[key].getValue() != -1) {
				array[key].setValue(-1);
				System.out.print("-->[" + key + "]:" + array[key].getValue());
				System.out.println();
				return true;
			} else {
				key = (key + 1) % DEFAULT_CAPACITY;
			}

		}
		return false;
	}

	public void deleteFile() {
		if (file.exists()) {
			file.delete();
		}
	}

	public void printArray() {
		for (int i = 0; i < 59; i++) {
			if (array[i] == null) {
				System.out.println("null");
				continue;
			}
			System.out.println("[" + i + "]" + array[i].getValue());
		}
	}

	public static void main(String[] args) throws IOException {

		HashTable linearHt = new HashTable();
		HashTable quadraticHt = new HashTable();
		HashTable doubleHt = new HashTable();

		BufferedReader data1 = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/Data1.txt"));
		BufferedReader data2 = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/Data2.txt"));
		BufferedReader data3 = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/Data3.txt"));

		linearHt.deleteFile();
		String line = null;
		System.out.println("==============선형조사==============");
		while ((line = data1.readLine()) != null) {
			System.out.print(line);
			linearHt.linear_insert(Integer.parseInt(line));
		}
		
		System.out.println();
		System.out.println("=============이차원조사=============");
		line = null;
		data1 = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/Data1.txt"));
		while ((line = data1.readLine()) != null) {
			System.out.print(line);
			quadraticHt.quadratic_insert(Integer.parseInt(line));
		}

		System.out.println();
		System.out.println("==============더블해싱==============");
		data1 = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/Data1.txt"));
		while ((line = data1.readLine()) != null) {
			System.out.print(line);
			doubleHt.double_insert(Integer.parseInt(line));
		}
		System.out.println("선형조사 충돌 횟수:" + linearHt.getCount());
		System.out.println("이차원조사 충돌 횟수:" + quadraticHt.getCount());
		System.out.println("더블해싱 충돌 횟수:" + doubleHt.getCount());

		System.out.println("---------Data2 File Search---------");
		line = null;
		while ((line = data2.readLine()) != null) {
			linearHt.linear_search(Integer.parseInt(line));
			// quadraticHt.quadratic_insert(Integer.parseInt(line));
			// doubleHt.double_insert(Integer.parseInt(line));
		}

		System.out.println("---------Data3 File Delete---------");
		line = null;
		while ((line = data3.readLine()) != null) {
			System.out.print(line);
			linearHt.linear_delete(Integer.parseInt(line));
		}
		linearHt.printArray();

	}

}

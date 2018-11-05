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
	// �⺻ �迭�� ũ��
	private final int DEFAULT_CAPACITY = 59;
	private Node[] array;
	private int count;

	public HashTable() {
		array = new Node[DEFAULT_CAPACITY];
	}

	static class Node {
		int key; // Node ���� Ű
		int value; // Node ���� Ű�� ����Ű�� ��

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

	// Ű�� ���� hash ���� ���ϴ� �޼ҵ�
	// �ܼ��� Ű�� ���ڿ��� �ٲ��� �ش� ������ �ƽ�Ű(ASCII) ���� ���� �̿��ϴ� �ؽ� �Լ� ���
	// �迭�� ũ�⸦ ���� �ʱ� ���� ������ ����(Modular)�� ���

	public int hash(int value) {

		return value % DEFAULT_CAPACITY;
	}


	// -------------------------------��������-------------------------------//
	public void linear_insert(int value) throws IOException {
		int key = hash(value);
		while (array[key] != null && array[key].getKey() != key) {
			count++;
			key = (key + 1) % DEFAULT_CAPACITY;
		}

		array[key] = new Node(key, value);

	}

	// -------------------------------����������-------------------------------//
	public void quadratic_insert() {

	}

	// -------------------------------�����ؽ�-------------------------------//
	public void double_insert() {

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

	//�ش� Ű ���� �ִ��� Ȯ��
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

	// �迭 ��Ҹ� ����ϱ� ���� �޼ҵ�
	void print() {
		for (int i = 0; i < array.length; ++i)
			System.out.println(array[i] == null ? "----"+"["+(i+1)+"]" : array[i].getValue()+" "+"["+(i+1)+"]");
		System.out.println("***COUNT***:"+count);
		System.out.println("--------------------------------------------------------------------");
	}

	public static void main(String[] args) throws IOException {

		HashTable ht = new HashTable();
		Node node;

		BufferedReader br = new BufferedReader(new FileReader("E:/algorithm/Al06_20181105/src/Data1.txt"));

		for (int i = 0; i < 24; i++) {
			String line = br.readLine();
			if (line == null) {
				break;
			} else {
				ht.linear_insert(Integer.parseInt(line));
			}
		}
		ht.print();

	}

}

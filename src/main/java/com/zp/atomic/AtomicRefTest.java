package com.zp.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefTest {
	
	static AtomicReference<User> atomicRef = new AtomicReference<User>();
	
	public static void main(String[] args) {
		User user = new User("zp",18);
		atomicRef.set(user);
		User user1 = new User("mark",29);
		atomicRef.compareAndSet(user, user1);
		System.out.println(user);
		System.out.println(user1);
		System.out.println(atomicRef.get());
		System.out.println(atomicRef.get() == user1);
	}
	
	static class User{
		String name;
		int old;
		public User(String name,int old){
			this.name = name;
			this.old = old;
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", old=" + old + "]";
		}
		
	}
}

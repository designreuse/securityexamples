package com.example.demo.entity;

public class View {
	 public interface Anonymous {}
	 public interface Simple extends Anonymous {}
	 public interface Extended extends Simple {}
	 public interface Detail extends Extended {}
	 public interface Admin extends Detail {}
}

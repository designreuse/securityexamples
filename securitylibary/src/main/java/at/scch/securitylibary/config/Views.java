package at.scch.securitylibary.config;

public class Views {
	 public interface Anonymous {}
	 public interface Simple extends Anonymous {}
	 public interface Extended extends Simple {}
	 public interface Detail extends Extended {}
	 public interface Admin extends Detail {}
}

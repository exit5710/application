package rmi.client;

import rmi.server.MyRemote;

import java.rmi.Naming;

public class MyRemoteClient {
	private void go() {
		try {
			MyRemote myRemote = (MyRemote) Naming.lookup("rmi://localhost/RemoteServer");
			String msg = myRemote.sayHello();
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// CTRL+F9 컴파일
		// C:\java\IdeaProjects\application\out\production\Unit>java rmi.client.MyRemoteClient
		new MyRemoteClient().go();
	}
}
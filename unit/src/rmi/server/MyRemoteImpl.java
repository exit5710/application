package rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
	protected MyRemoteImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHello() {
		return "Server says, 'Hey~!'";
	}

	public static void main(String[] args) {
		// CTRL+F9 컴파일
		// C:\java\IdeaProjects\application\out\production\Unit>rmic rmi.server.MyRemoteImpl (MyRemoteImpl_Stub.class 생성)
		// rmiregistry
		// java rmi.server.MyRemoteImpl

		try {
			MyRemote service = new MyRemoteImpl();
			Naming.rebind("RemoteServer", service);
			System.out.println("Hello remote object bound to the registry and ready to service incoming client calls...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
import java.net.*;
import java.io.*;

class LoginClient{
	public static void main(String[] args)throws Exception {
		Socket s=new Socket("192.168.3.184",10008);
		BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(s.getOutputStream(),true);

		BufferedReader bufin=new BufferedReader(new InputStreamReader(s.getInputStream()));

		for (int i=0;i<3 ;i++ ) {
			String line=bufr.readLine();
			if(line==null){
				break;
			}
			pw.println(line);
			String info=bufin.readLine();
			System.out.println(info);
			if (info.contains("hello")) {
				break;
			}
		}
		bufr.close();
		s.close();
	}
}

class LoginServer{
	public static void main(String[] args)throws Exception {
		ServerSocket ss=new ServerSocket(10008);

		while(true){
			Socket s=ss.accept();
			new Thread(new UserThread(s)).start();
		}

	}
}


class UserThread implements Runnable{
	private Socket s;
	UserThread(Socket s){
		this.s=s;
	}
	public void run(){
		try{
			String ip=s.getInetAddress().getHostAddress();
			System.out.println(ip+"......connected");
			for(int i=0;i<3;i++){
				BufferedReader bufIn=new BufferedReader(new InputStreamReader(s.getInputStream()));
				String name=bufIn.readLine();
				BufferedReader bufr=new BufferedReader(new FileReader("D:\\javapractice"));
				PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
				String line=null;
				boolean flag=false;
				while((line=bufr.readLine())!=null){
					if(line.equals(name)){
						flag=true;
						break;
					}
				}
				if(flag){
					System.out.println(name+"has loaded");
					pw.println(name+",--hello!");
					break;
				}else{
					System.out.println(name+"try to loading");
					pw.println(name+",--your name isn't corrected!");
					
				}


			}
			s.close();
		}
		catch(Exception e){
			throw new RuntimeException("sever Exception");
		}

	}
}
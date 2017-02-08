class ArrayTest{
	public static void main(String[] args){
		int[] arr={8,7,6,5,4,3,2,1};
		/*int x=9;
		int p=halfSearch(arr,x);
		System.out.print(p);
		*/
		for (int x=arr.length-1;x>=0 ;x-- ) {
			for (int y=0;y<x ;y++ ) {
				if (arr[y]>arr[y+1]) {
					int temp=arr[y];
					arr[y]=arr[y+1];
					arr[y+1]=temp;
					
				}
				
			}


		
		}	
		


		for (int i=0;i<arr.length ;i++ ) {
			System.out.println(arr[i]);
			
		}
	}
	

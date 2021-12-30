import java.util.Random;

public class Sorting {
	static void selection(int[]aa) {
		for(int i=aa.length-1;i>=0;i--) {
			int max=0,maxi=0;
			for(int j=0;j<=i;j++)
				if(max<aa[j]) {
					max=aa[j];maxi=j;
				}
			aa[maxi]=aa[i];
			aa[i]=max;
		}
		return;
	}
	static void bubble(int[]aa) {
		for(int i=aa.length-1;i>0;i--) {
			for(int j=0;j<i;j++)
				if(aa[j]>aa[j+1]) {
					int tmp=aa[j];
					aa[j]=aa[j+1];
					aa[j+1]=tmp;
				}
		}
	}
	static void insertion(int[]aa) {
		for(int i=1;i<aa.length;i++) {
			int im = aa[i];
			int j;
			for(j=i-1;j>=0&&aa[j]>im;j--)
				aa[j+1]=aa[j];
			aa[j+1]=im;
		}
	}
	static int[] mer;
	static void merge(int[]aa,int p,int q, int r) {
		//int[] bb = new int[aa.length];
		int i=p,j=q+1,k=p;
		while(i<=q&&j<=r) {
			if(aa[i]<=aa[j]) mer[k++]=aa[i++];
			else mer[k++]=aa[j++];
		}
		if(i>q) {
			for(int l=j;l<=r;l++) mer[k++]=aa[l];
		}
		else {
			for(int l=i;l<=q;l++) mer[k++]=aa[l];
		}
		for(int l=p;l<=r;l++)
			aa[l]=mer[l];
	}
	static void mergeeee(int[]aa, int q, int r) {
		int mid;
		if(q<r) {
			mid=(q+r)/2;
			mergeeee(aa, q, mid);
			mergeeee(aa, mid+1, r);
			merge(aa, q, mid, r);
			
		}
	}
	
	
	static int partition(int[]aa,int p,int r) {
		int pivot = aa[r];
		int l = p,h=r;
		while(l<h) {
			while(aa[l]<pivot&&l<h) {
				l++;
			}
			while(aa[h]>=pivot&&l<h) {
				h--;
			}
			int tmp=aa[l];
			aa[l]=aa[h];
			aa[h]=tmp;
		}
		int tmp=aa[r];
		aa[r]=aa[h];
		aa[h]=tmp;
		return h;
	}
	static void quick(int[]aa,int p,int r) {
		if(p<r) {
			int pivot = partition(aa, p, r);
			quick(aa, p, pivot-1);
			quick(aa, pivot+1, r);
		}
	}
	static int[] counting(int[]aa,int p) {
		int[] bb = new int[p];
		int[] cc = new int[aa.length];
		for(int i=0;i<aa.length;i++)
			bb[aa[i]]++;
		for(int i=1;i<bb.length;i++)
			bb[i]+=bb[i-1];
		for(int i=aa.length-1;i>=0;i--) {
			bb[aa[i]]--;
			cc[bb[aa[i]]]=aa[i];
		}
		return cc;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		
		for(int j=1;j<6;j++) {
			int num=1;
			for(int i=0;i<=j;i++)
				num*=10;
			int[] aa = new int[num];
			for(int i=0;i<num;i++)
				aa[i]=random.nextInt(num);
			int[] aa1=aa.clone(),aa2=aa.clone(),aa3=aa.clone(),aa4=aa.clone();

			mer = new int[num];
			/*
			for(int i=0;i<aa.length;i++)
				System.out.print(aa[i]+" ");
				*/
			System.out.println();
			
			long bT=System.nanoTime();
			selection(aa);
			long aT=System.nanoTime();
			long time = aT-bT;
			System.out.println("aa["+(num)+"] 선택 정렬 시간(ns) : "+time);
			
			bT=System.nanoTime();
			insertion(aa1);
			aT=System.nanoTime();
			time = aT-bT;
			System.out.println("aa["+(num)+"] 삽입 정렬 시간(ns) : "+time);
			
			bT=System.nanoTime();
			mergeeee(aa2,0,aa2.length-1);
			aT=System.nanoTime();
			time = aT-bT;
			System.out.println("aa["+(num)+"] 병합 정렬 시간(ns) : "+time);

			bT=System.nanoTime();
			quick(aa3,0,aa3.length-1);
			aT=System.nanoTime();
			time = aT-bT;
			System.out.println("aa["+(num)+"]   퀵 정렬 시간(ns) : "+time);
			
			bT=System.nanoTime();
			/*aa4_1=*/counting(aa4, aa.length);
			aT=System.nanoTime();
			time = aT-bT;
			System.out.println("aa["+(num)+"] 계수 정렬 시간(ns) : "+time);
			
			/*
			for(int i=0;i<aa4_1.length;i++)
				System.out.print(aa4_1[i]+" ");
				*/
			System.out.println();System.out.println();
		}
	}

}

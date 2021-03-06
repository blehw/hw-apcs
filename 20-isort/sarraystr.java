public class sarraystr {
    private String[] data;
    private int last;
    private String letters="abcdefghijklmnopqrstuvwxyz";

    public sarraystr() {
	data = new String[10];
	last = -1;
    }

    public String toString() {
	String s = "";
	for (int i=0;i<=last;i++) {
	    s += i+": "+data[i] + "\n";
	}
	return s;
    }

    private void makeArray() {
	String[] newArray = new String[data.length + 1];
	for (int i=0;i<data.length;i++) {
	    newArray[i] = data[i];
	}
	data = newArray;
    }

    public boolean add(String s) {
        if (last<data.length) {
	    data[last +1] = s;
	    last +=1;
	}
	if (last==data.length) {
	    makeArray();
	    data[last +1] = s;
	    last +=1;
	}
	return true;
    }
    
    public void add(int index, String s) {
         if (last<index) {
	     if (index<data.length) {
		 data[index] = s;
		 last = index;
	     } else {
		 while (data.length<index+1) {
		     makeArray();
		 }
		 data[index] = s;
		 last = index;
	     }
	 } else {
	     makeArray();
	     for (int k=last;k>=index;k=k-1) {
		 data[k +1] = data[k];
	     }
	     data[index] = s; 
	     last +=1;
	 }
    }

    public int size() {
	int counter = 0;			
	for (int i=0;i<data.length;i++) {
	    if (data[i]!=null) {
		counter++;
	    }
	}
	return counter;
    }
    
    public String get(int index) {
	return data[index];
    }
   
    public String set(int index, String s) {
        String oldstr = data[index];
	data[index] = s;
	return oldstr;
    }    

    public void remove(int index) {
	try {
	    for (int i=index;i<last;i++) {
		data[i] = data[i+1];
	    }
	    data[last] = null;
	    last = last - 1;
	} catch (Exception e) {
	    System.out.println("Index out of bounds");
	}
    }

    public void fill() {
	for (int i=0;i<25;i++) {
	    data[i] = letters.substring(25-i,25-i+1);
	    last = last + 1;
	}
    }

    /*
      isort and ssort are doing comparisons and assignments
      Arrays.sort(data); sorts the entire array
      you can also sort only a part of the array
     */

    //n*(n-1)/2, n^2 algorithm
    public void isort() {
	int cCount = 0;
	int aCount = 0;
	String val;
	int k;
	for (int i=0;i<last+1;i++) {
	    val = data[i];
	    aCount = aCount + 1;
	    for (k=i;k>0&&val.compareTo(data[k-1])<=0;k--) {
		cCount = cCount + 1;
		data[k] = data[k-1];
		aCount = aCount + 1;
	    }
	    data[k]=val;
	    aCount = aCount + 1;
	}
	System.out.println("isort comparison count: " + cCount);
	System.out.println("isort assignment count: " + aCount);
    }

    //n^2 algorithm
    public void ssort() {
	int cCount = 0;
	int aCount = 0;
	String min = "";
	String oldstr="";
	int index=0;
	for (int i=0;i<=last;i++) {
	    int k=i;
	    min=data[k];
	    aCount = aCount + 2;
	    for (k=i;k<=last;k++) {
		if (data[k].compareTo(min)<0) {
		    cCount = cCount + 1;
		    min = data[k];
		    index=k;
		    aCount = aCount + 2;
		}
	    }
	    oldstr = data[i];
	    data[i] = min;
	    data[index] = oldstr;
	    aCount = aCount + 3;
	}
	System.out.println("ssort comparison count: " + cCount);
	System.out.println("ssort assignment count: " + aCount);
    }

    public boolean bcheck() {
	for (int i=0;i<size()-1;i++) {
	    if (data[i].compareTo(data[i+1])>0) {
		return true;
	    }
	}
	return false;
    }

    public void bsort() {
	String val;
	while (bcheck()) {
	    for (int i=0;i<size()-1;i++) {
		if (data[i].compareTo(data[i+1])>0) {
		    val = data[i];
		    data[i] = data[i+1];
		    data[i+1] = val;
		}
	    }
	}
    }
    
    public static void main(String[] args) {
	sarraystr s1 = new sarraystr();
	/*
        s1.add("c");
	s1.add("z");
	s1.add("b");	
	s1.add("a");
	s1.add("q");
	6 comparisons, 16 assignments
	s1.fill(); (k to b, reverse order)
	45 comparisons, 65 assignments
	*/
	s1.fill();
	System.out.println("before isort:\n"+ s1);
	s1.isort();
	System.out.println("after isort:\n"+s1);
	sarraystr s2 = new sarraystr();
	/*
	s2.add("c");
	s2.add("z");
	s2.add("b");	
	s2.add("a");
	s2.add("q");
	5 comparisons, 35 assignments
	s1.fill(); (k to b, reverse order)
	25 comparisons, 100 assignments
	*/
	System.out.println("before ssort:\n"+ s2);
	s2.ssort();
	System.out.println("after ssort:\n"+s2);
	sarraystr s3 = new sarraystr();
	s3.add("c");
	s3.add("z");
	s3.add("b");	
	s3.add("a");
	s3.add("q");
	System.out.println("before bsort:\n"+s3);
	s3.bsort();
	System.out.println("after bsort:\n"+s3);
    }

}

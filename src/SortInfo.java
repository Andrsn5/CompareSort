public class SortInfo {
    private int cmpCount;
    private int changeCount;
    private long timeMs;

    public SortInfo(int cmpCount, int changeCount, long timeMs) {
        this.cmpCount = cmpCount;
        this.changeCount = changeCount;
        this.timeMs = timeMs;}

        public int getCmpCount() { return cmpCount; }

        public int getchangeCount() { return changeCount; }

        public long getTimeMs() { return timeMs; }

    public static SortInfo bubleSortInfo(Integer[] arr){
        int cmp=0;
        int change=0;
        long startTime = System.nanoTime();
        for (int i =0 ; i< arr.length;i++){
            for (int j=i;j<arr.length-1;j++){
                if (arr[i]>arr[i+1]){
                    swap(arr,i,i+1);
                    change++;
                }
                cmp++;
            }
        }
        long finishTime = System.nanoTime();
        return new SortInfo(cmp,change,finishTime-startTime);
    }
    public static SortInfo shakerSortInfo(Integer[] arr){
        int cmp=0;
        int change=0;
        long startTime = System.nanoTime();
        int left=0,right= arr.length-1,control=right;
        while (left<right){
            for (int i = left;i<right;i++){
                if (arr[i]<arr[i+1]){
                    swap(arr,i,i+1);
                    control=i;
                    change++;
                }
                cmp++;
            }
            right=control;
            for (int j=right;j>left;j--){
                if(arr[j]>arr[j-1]){
                    swap(arr,j,j-1);
                    control=j;
                }
                cmp++;
            }
            left=control;
        }
        long finishTime = System.nanoTime();
        return new SortInfo(cmp,change,finishTime-startTime);
    }
    public static Integer[] swap(Integer[] arr , int i , int j){
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
        return arr;
    }
}
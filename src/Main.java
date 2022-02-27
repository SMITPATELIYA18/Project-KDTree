public class Main {

    public static void main(String[] args) {
        int[] demoKey = {25,45,50,50,50,70,85,30,25,45,50,60};
        int[] demoValue = {60,60,75,100,120,110,140,260,400,350,275,260};
        KDTree kdTree = new KDTree();
        for(int i=0;i< demoKey.length;i++) {
            kdTree.insert(demoKey[i],demoValue[i]);
        }
        System.out.println(kdTree);
    }
}

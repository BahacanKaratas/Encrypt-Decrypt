class wrongDimensionException extends Exception{
    public wrongDimensionException(String message){
        System.out.println(message);
        System.exit(0);
    }
    public wrongDimensionException(){
        super("default");
        System.exit(0);
    }
}
public class MatMul{
    //There is two generic types here because I do not know how user will call this method.
    //This method throws my custom Exception
    public static <A extends Number,B extends Number> Double[][] multiply(A[][] matrixNo1,B[][] matrixNo2)throws wrongDimensionException{
        if(matrixNo1[0].length!=matrixNo2.length){
            //I send this message to be printed by user in main like suggested in the question
            throw new wrongDimensionException("Matrisler Carpilamaz");
        }
        else{
            //now I need an Double[][] array to return
            Double[][] returnThis=new Double[matrixNo1.length][matrixNo2[0].length];
            //not recieve a null pointer exception I need to fill this with something (I will with 0.0)
            for(int i=0;i<matrixNo1.length;i++){
                for(int j=0;j<matrixNo2[0].length;j++){
                    returnThis[i][j]=0.0;
                }
            }
            //now all I need to do is multiplication
            for (int i =0;i<matrixNo1.length;i++){
                for(int j=0;j<matrixNo2[0].length;j++){
                    for(int z=0; z<matrixNo1[0].length;z++){
                        //now there may be null elements in the matrixes so I need to fix this problem. I will put 0 if there is a null element in a matrix
                        //This will prevent further problems.
                        if(matrixNo1[i][z]==null || matrixNo2[z][j]==null){
                            returnThis[i][j]=0.0;
                        }
                        else{
                            returnThis[i][j]+=matrixNo1[i][z].doubleValue() * matrixNo2[z][j].doubleValue();
                        }
                    }
                }
            }
            return returnThis;
        }
    }
}

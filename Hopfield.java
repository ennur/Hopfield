package hopfield;

import java.io.IOException;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Hopfield;
import org.neuroph.util.NeuralNetworkType;

public class Hopfield {
    public static void main(String[] args) throws IOException {
           DataSet egitim =  new DataSet(9);
           YSA rsm = new YSA("H.bmp");
           double[] pixel = rsm.pixelGetirUclu();
           egitim.add(new DataSetRow(pixel));
           
           rsm = new YSA("T.bmp");
           pixel = rsm.pixelGetirUclu();
           egitim.add(new DataSetRow(pixel));
           
           Hopfield ag = new Hopfield(9);
           ag.setNetworkType(NeuralNetworkType.UNSUPERVISED_HEBBIAN_NET);
           ag.learn(egitim);
           
           rsm = new YSA("H_test.bmp");
           pixel = rsm.pixelGetirUclu();
           ag.setInput(pixel);
           ag.calculate();
           ag.calculate();
           double[] cikti = ag.getOutput();
           resim olusanResim = new YSA(cikti);
    }
    
}

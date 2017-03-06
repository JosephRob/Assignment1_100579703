package Asmt1;

import java.text.DecimalFormat;

public class TestFile {
    private String filename;
    private double spamProbability;
    private String actualClass;
    private String predictedClass;

    public TestFile(String filename, double spamProbability, String actualClass) {
        this.filename = filename;
        this.spamProbability = spamProbability;
        this.actualClass = actualClass;
    }
    public String getFileName() { return this.filename; }
    public double getSpamProbability() { return this.spamProbability; }
    public String getSpamProbRounded() {
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(this.spamProbability);
    }
    public String getActualClass() {    return this.actualClass;   }
    public void setFilename(String value) { this.filename = value;  }
    public void setSpamProbability(double val){ this.spamProbability = val; }
    public void setActualClass(String value) { this.actualClass = value; }
    public void setPredictedClass(String value) { this.predictedClass = value; }
    public String getPredictedClass() {    return this.predictedClass;   }
}
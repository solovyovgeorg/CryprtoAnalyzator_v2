package view;

import operations.Operation;

/** Предназначение класса - DTO Data Transfer Object - хранение данных пользовательского ввода, для передачи контроллеру */
public class ViewData {
    private String src;
    private String out;
    private String sample;
    private String textSample;
    private int key;
    private Operation operation;


    public Operation getOperation(){
        return this.operation;
    }
    public void setOperation(Operation operation){
        this.operation = operation;
    }
    public String getSrc(){
        return this.src;
    }
    public void setSrc(String src){
        this.src = src;
    }

    public String getOut(){
        return this.out;
    }
    public void setOut(String out){
        this.out = out;
    }
    public String getSample(){
        return this.sample;
    }
    public void setSample(String sample){
        this.sample = sample;
    }

    public int getKey() {
        return this.key;
    }
    public void setKey(int key){
            this.key = key;
    }

    public String getTextSample() {
        return textSample;
    }

    public void setTextSample(String textSample) {
        this.textSample = textSample;
    }
}

package com.corona.insights.etl;

public interface ETLProcessor {

    public void extract();

    public void transform();

    public void load();

}

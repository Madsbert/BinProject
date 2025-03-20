package org.example.binproject.Services;

import org.example.binproject.Domain.Measurements;

import java.util.List;

public interface CalculationsInterface {

    public List<Integer> calculateStatistics(List<Measurements> measurements);
}

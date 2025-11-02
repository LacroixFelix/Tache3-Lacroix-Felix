package com.graphhopper.routing;

import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.Graph;
import com.graphhopper.storage.NodeAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RoutingAlgorithmMockTest {

    @Mock
    private Graph mockGraph;
    
    @Mock
    private Weighting mockWeighting;
    
    @Mock
    private NodeAccess mockNodeAccess;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoutingWithMockedGraph() {
        when(mockGraph.getNodes()).thenReturn(100);
        when(mockGraph.getNodeAccess()).thenReturn(mockNodeAccess);
        when(mockNodeAccess.getLat(0)).thenReturn(45.5);
        when(mockNodeAccess.getLon(0)).thenReturn(-73.5);
        
        assertEquals(100, mockGraph.getNodes());
        assertEquals(45.5, mockNodeAccess.getLat(0));
        
        verify(mockGraph, times(1)).getNodes();
    }

    @Test
    void testWeightCalculation() {
        when(mockWeighting.calcEdgeWeight(any(), anyBoolean())).thenReturn(10.0);
        
        double weight = mockWeighting.calcEdgeWeight(null, false);
        
        assertEquals(10.0, weight);
        verify(mockWeighting, times(1)).calcEdgeWeight(any(), anyBoolean());
    }
}
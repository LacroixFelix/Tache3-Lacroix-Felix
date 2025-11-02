package com.graphhopper.storage;

import com.graphhopper.util.EdgeIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GraphStorageMockTest {

    @Mock
    private NodeAccess mockNodeAccess;
    
    @Mock
    private EdgeIterator mockEdgeIterator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNodeAccess() {
        when(mockNodeAccess.getLat(0)).thenReturn(45.5);
        when(mockNodeAccess.getLon(0)).thenReturn(-73.5);
        
        assertEquals(45.5, mockNodeAccess.getLat(0));
        assertEquals(-73.5, mockNodeAccess.getLon(0));
        
        verify(mockNodeAccess, times(1)).getLat(0);
        verify(mockNodeAccess, times(1)).getLon(0);
    }

    @Test
    void testEdgeIteration() {
        when(mockEdgeIterator.next()).thenReturn(true, true, false);
        when(mockEdgeIterator.getDistance()).thenReturn(1000.0, 2000.0);
        
        assertTrue(mockEdgeIterator.next());
        assertEquals(1000.0, mockEdgeIterator.getDistance());
        
        assertTrue(mockEdgeIterator.next());
        assertEquals(2000.0, mockEdgeIterator.getDistance());
        
        assertFalse(mockEdgeIterator.next());
        
        verify(mockEdgeIterator, times(3)).next();
    }
}
package pee.mecproc.mem;

import java.util.Collections;
import java.util.LinkedList;

import pee.mecproc.No;

public class MemoriaLIFO extends MemoriaProcura{
	
	//procura de comprimento
	public MemoriaLIFO(){
		super(Collections.asLifoQueue(new LinkedList<No>()));
	}
}

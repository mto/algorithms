/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.mto.algorithms.graph;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/9/12
 */
public class TestGraph extends TestCase
{

   @Test
   public void testDFS()
   {
      //Setup the model
      Node a = new Node("A");
      Node b = new Node("B");
      Node c = new Node("C");
      Node d = new Node("D");
      Node e = new Node("E");
      Node f = new Node("F");
      Node g = new Node("G");
      Node h = new Node("H");
      Node i = new Node("I");
      Node k = new Node("K");

      //Setup the vertices
      Vertex<Node> v_a = new Vertex<Node>(a);
      Vertex<Node> v_b = new Vertex<Node>(b);
      Vertex<Node> v_c = new Vertex<Node>(c);
      Vertex<Node> v_d = new Vertex<Node>(d);
      Vertex<Node> v_e = new Vertex<Node>(e);
      Vertex<Node> v_f = new Vertex<Node>(f);
      Vertex<Node> v_g = new Vertex<Node>(g);
      Vertex<Node> v_h = new Vertex<Node>(h);
      Vertex<Node> v_i = new Vertex<Node>(i);
      Vertex<Node> v_k = new Vertex<Node>(k);

      //Setup the edges
      v_a.addChild(v_b).addChild(v_c).addChild(v_h);
      v_b.addChild(v_a).addChild(v_d).addChild(v_e).addChild(v_c);
      v_c.addChild(v_a);
      v_d.addChild(v_b);
      v_e.addChild(v_b).addChild(v_f).addChild(v_g);
      v_f.addChild(v_e);
      v_g.addChild(v_e);
      v_h.addChild(v_a).addChild(v_i).addChild(v_k);
      v_i.addChild(v_h);
      v_k.addChild(v_h);

      //Setup graph and test
      Graph<Node> graph = new Graph<Node>(v_a);
      final Operator<Node> numerator = new Numerator(0);
      graph.depthFirstSearch(numerator);

      Vertex[] vertices = new Vertex[]{v_a, v_b, v_c, v_d, v_e, v_f, v_g, v_h, v_i, v_k};
      int[] indices = new int[]{0, 1, 6, 2, 3, 4, 5, 7, 8, 9};

      for (int count = 0; count < vertices.length; count++)
      {
         assertEquals(indices[count], ((Vertex<Node>)vertices[count]).getWrappedNode().getIndex());
      }
   }

   @Test
   public void testBFS()
   {
      //Setup the model
      Node a = new Node("A");
      Node b = new Node("B");
      Node c = new Node("C");
      Node d = new Node("D");
      Node e = new Node("E");
      Node f = new Node("F");
      Node g = new Node("G");
      Node h = new Node("H");
      Node i = new Node("I");
      Node k = new Node("K");

      //Setup the vertices
      Vertex<Node> v_a = new Vertex<Node>(a);
      Vertex<Node> v_b = new Vertex<Node>(b);
      Vertex<Node> v_c = new Vertex<Node>(c);
      Vertex<Node> v_d = new Vertex<Node>(d);
      Vertex<Node> v_e = new Vertex<Node>(e);
      Vertex<Node> v_f = new Vertex<Node>(f);
      Vertex<Node> v_g = new Vertex<Node>(g);
      Vertex<Node> v_h = new Vertex<Node>(h);
      Vertex<Node> v_i = new Vertex<Node>(i);
      Vertex<Node> v_k = new Vertex<Node>(k);

      //Setup the edges
      v_a.addChild(v_b).addChild(v_c).addChild(v_h);
      v_b.addChild(v_a).addChild(v_d).addChild(v_e).addChild(v_c);
      v_c.addChild(v_a);
      v_d.addChild(v_b);
      v_e.addChild(v_b).addChild(v_f).addChild(v_g);
      v_f.addChild(v_e);
      v_g.addChild(v_e);
      v_h.addChild(v_a).addChild(v_i).addChild(v_k);
      v_i.addChild(v_h);
      v_k.addChild(v_h);

      //Setup graph and test
      Graph<Node> graph = new Graph<Node>(v_a);
      final Operator<Node> numerator = new Numerator(0);
      graph.breadthFirstSearch(numerator);

      Vertex[] vertices = new Vertex[]{v_a, v_b, v_c, v_d, v_e, v_f, v_g, v_h, v_i, v_k};
      int[] indices = new int[]{0, 1, 2, 4, 5, 8, 9, 3, 6, 7};

      for (int count = 0; count < vertices.length; count++)
      {
         assertEquals(indices[count], ((Vertex<Node>)vertices[count]).getWrappedNode().getIndex());
      }
   }

   class Numerator implements Operator<Node>
   {
      private int counter;

      public Numerator(int c)
      {
         counter = c;
      }

      @Override
      public void operate(Vertex<Node> vertex)
      {
         vertex.getWrappedNode().setIndex(counter);
         counter++;
      }
   }
}

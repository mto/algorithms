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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/9/12
 */
public class Graph<N>
{
   private Vertex<N> rootVertex;

   public Graph(Vertex<N> rootVertex)
   {
      this.rootVertex = rootVertex;
   }

   public void depthFirstSearch(final Operator<N> operator)
   {
      final Map<Vertex<N>, Object> visitedVertices = new HashMap<Vertex<N>, Object>();
      final Object mock = new Object();

      final Stack<Vertex<N>> stack = new Stack<Vertex<N>>();
      stack.push(rootVertex);
      while (!stack.isEmpty())
      {
         Vertex<N> vertex = stack.peek();
         if(visitedVertices.get(vertex) == null)
         {
            operator.operate(vertex.getWrappedNode());
            visitedVertices.put(vertex, mock);
         }
         boolean childrenVisited = true;
         for(Vertex<N> child : vertex.children())
         {
            if(visitedVertices.get(child) == null)
            {
               operator.operate(child.getWrappedNode());
               visitedVertices.put(child, mock);
               stack.push(child);
               childrenVisited = false;
               break;
            }
         }

         if(childrenVisited)
         {
            stack.pop();
         }
      }
   }

   public void breadthFirstSearch(final Operator<N> operator)
   {
      final Map<Vertex<N>, Object> visitedVertices = new HashMap<Vertex<N>, Object>();
      final Object mock = new Object();

      final LinkedList<Vertex<N>> fifoQueue = new LinkedList<Vertex<N>>();
      fifoQueue.add(rootVertex);
      while(!fifoQueue.isEmpty())
      {
         Vertex<N> vertex = fifoQueue.getFirst();
         if(visitedVertices.get(vertex) == null)
         {
            operator.operate(vertex.getWrappedNode());
            visitedVertices.put(vertex, mock);
         }
         boolean childrenVisited = true;
         for(Vertex<N> child : vertex.children())
         {
            if(visitedVertices.get(child) == null)
            {
               operator.operate(child.getWrappedNode());
               visitedVertices.put(child, mock);
               fifoQueue.addLast(child);
               childrenVisited = false;
               break;
            }
         }

         if(childrenVisited)
         {
            fifoQueue.removeFirst();
         }
      }
   }
}

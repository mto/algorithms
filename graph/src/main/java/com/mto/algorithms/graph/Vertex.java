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

import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/9/12
 */
public class Vertex<N>
{

   private N wrappedNode;

   private final List<Vertex<N>> children;

   public Vertex(N wrappedNode)
   {
      this(wrappedNode, null);
   }

   public Vertex(N wrappedNode, List<Vertex<N>> children)
   {
      if (wrappedNode == null)
      {
         throw new IllegalArgumentException();
      }
      this.wrappedNode = wrappedNode;
      this.children = (children != null) ? children : new LinkedList<Vertex<N>>();
   }

   public List<Vertex<N>> children()
   {
      return children;
   }

   public Vertex<N> addChild(Vertex<N> v)
   {
      if(v == this || v.equals(this))
      {
         return this;
      }
      for(Vertex<N> child : children)
      {
         if(v == child || v.equals(child))
         {
            return this;
         }
      }
      children.add(v);
      return this;
   }

   public N getWrappedNode()
   {
      return wrappedNode;
   }
}

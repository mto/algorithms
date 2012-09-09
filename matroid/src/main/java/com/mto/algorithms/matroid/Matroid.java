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
package com.mto.algorithms.matroid;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 9/9/12
 */
public abstract class Matroid<E, S extends Set<E>>
{

   public abstract boolean accept(List<E> elements, E newElement);

   /**
    * Apply Greedy algorithm with the set and a given function
    *
    * @param set
    * @param function
    * @return
    */
   public List<E> applyGreedy(S set, final Function<E> function)
   {
      List<E> orderedList = new LinkedList<E>(set);
      Collections.sort(orderedList, new Comparator<E>() {
         @Override
         public int compare(E o1, E o2)
         {
            return function.evaluate(o2) - function.evaluate(o1);
         }
      });

      final List<E> result = new LinkedList<E>();
      for(E element : orderedList)
      {
         //Performance issue to make the list unmodifiable, but necessary to make call to accept method safe
         if(accept(Collections.unmodifiableList(result), element))
         {
            result.add(element);
         }
      }

      return result;
   }
}

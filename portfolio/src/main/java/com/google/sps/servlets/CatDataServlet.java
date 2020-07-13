// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/cat-data")
public class CatDataServlet extends HttpServlet {
  private Map<String, Integer> catVotes = new HashMap<>();
  
  @Override
  public void init() {
    catVotes.put("cat1", 512);
    catVotes.put("cat2", 346);
    catVotes.put("cat3", 412);
    catVotes.put("cat4", 378);
    catVotes.put("cat5", 667);
    catVotes.put("cat6", 265);
    catVotes.put("cat7", 176);
    catVotes.put("cat8", 325);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String cat = request.getParameter("cat");
    int currentVotes = catVotes.containsKey(cat) ? catVotes.get(cat) : 0;
    catVotes.put(cat, currentVotes + 1);
    
    // Redirect back to the HTML page.
    response.sendRedirect("/gallery.html");
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {    
    Gson gson = new Gson();
    String json = gson.toJson(catVotes);

    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}

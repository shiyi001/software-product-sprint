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

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  private ArrayList<String> comments;

  @Override
  public void init() {
    comments = new ArrayList<>();
    comments.add(
        "A ship in port is safe, but that is not what ships are for. "
            + "Sail out to sea and do new things. - Grace Hopper");
    comments.add("They told me computers could only do arithmetic. - Grace Hopper");
    comments.add("A ship in port is safe, but that's not what ships are built for. - Grace Hopper");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String new_comment = request.getParameter("new_comment");

    if (new_comment == null || new_comment.length() == 0) {
      response.setContentType("text/html");
      response.getWriter().println("Please enter an meaningful comment");
      return;
    } else {
      comments.add(new_comment);
    }
    
    // Redirect back to the HTML page.
    response.sendRedirect("/index.html");
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(comments);

    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}

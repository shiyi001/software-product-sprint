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
    comments.add("It is much easier to apologise than it is to get permission. - Grace Hopper");
    comments.add("If you can't give me poetry, can't you give me poetical science? - Ada Lovelace");
    comments.add("I am in a charming state of confusion. - Ada Lovelace");
    comments.add(
        "The Analytical Engine weaves algebraic patterns, "
            + "just as the Jacquard loom weaves flowers and leaves. - Ada Lovelace");
    comments.add(
        "Sometimes it is the people no one can imagine anything of "
            + "who do the things no one can imagine. - Alan Turing");
    comments.add("Those who can imagine anything, can create the impossible. - Alan Turing");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(comments);

    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}

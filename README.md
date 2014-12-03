jsf-bootstrap
=============

A simple implementation of basic Bootstrap CSS Components to be used in JSF Applications.

<h3>Usage</h3>

Define the
<pre>
xmlns:k="http://kesper-software.de/jsf-bootstrap"
</pre>
Namespace to your JSF page. 

You need to have a working bootstrap installation in your web application. I did not want to define a certain version 
you'll then need to stick with. Use your own version and feel free to adapt themes and/or change colors. And if you're going to use the <code>dataTable</code> Tag, you'll need in your webapp under <code>resources/images</code> three images:

<ul>
<li><code>sort_asc.png</code> - The image of an arrow pointing upwards.</li>
<li><code>sort_desc.png</code> - The image of an arrow pointing downwards.</li>
<li><code>sort_both.png</code> - An image that represents the unsorted state. Usually that is a grey version of arrows both up- and downwards.</li>
</ul>

<h3>Components</h3>

So far...

<ul>
<li><code>dataTable</code> - As the name suggests. It creates a simple table and let datatables.net do the magic.</li>
<li><code>calendar</code> - As the name suggests. But it has a bug, not working at the moment.</li>
<li><code>jumbotron</code> - A big Message display.</li>
<li><code>well</code> - A paragraph in a bootstrap well.</li>
<li><code>messages</code> - Messages rendered as Bootstrap alerts.</li>
<li><code>badge</code> - Badges.</li>
<li><code>panel</code> - Creates a Bootstrap Panel. You define header and footer as attribute.</li>
</ul>

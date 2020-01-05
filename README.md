# Flow Control
<strong>Projet SAR</strong> - Contrôle de flux sur un tampon dans un serveur multi-threadée T pour plusieurs producteurs et plusieurs consommateurs

# Get started 

<ul>
<li>git clone [this_repository_url]</li>
<li>Open your eclipse IDE and add the repository</li>
<li>Start to code</li>
</ul>

# Usage 

<ol>
  <li>Go to the src location </li>
  <li><strong>Run the server</strong><br/>
    java fr/dauphine/sar/flowcontrol.TServer [n] (the size of the buffer, 5 by default) 
<li><strong>Run the producer </strong><br/>
    java fr/dauphine/sar/flowcontrol.Producer (You can run as many as you want, just do it in a different terminal)
<li><strong>Run the consumer </strong><br/>
    java fr/dauphine/sar/flowcontrol.Consumer (You can run as many as you want, just do it in a different terminal)
</ol>

# Authors 

Alban Tiacoh</br>
Lucas Dedieu</br>
Thierno Bah Moussa</br>


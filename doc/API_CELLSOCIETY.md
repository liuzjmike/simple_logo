API Critique
=============
The public classes of Model and numRows, numCols, get, setShape, setNeighborPattern methods of Grid should be the back-end external API. The setModel method of GridView and GraphView and GUI.show() should be front-end external API. The other public methods should be internal APIs.
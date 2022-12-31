const express = require('express');
const path = require('path')
const app = express();
const PORT = 3002;

app.get('/records', (req, res) => {
    // console.log(req.query);
    res.sendFile(path.join(__dirname, `./pages/${req.query.pageno}.json`));
});
app.use(express.static('static'))

app.listen(PORT, () => console.log(`Server listening on port: ${PORT}`));

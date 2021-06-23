module.exports = {
    devServer: {
        // Backend z.B. per Einstellung
        // in application.properties (Spring)
        // auf server.port=9090
        // Pfade, die mit /api anfangen, umleiten proxy: {
        // setzt voraus, dass Spring Backend per application.properties auf server.port=9090 laeuft
        proxy: {
            '^/(api|foto)': {
                target: 'http://localhost:9090/',
                ws: true,
                secure: false
            },
        }
    }
}
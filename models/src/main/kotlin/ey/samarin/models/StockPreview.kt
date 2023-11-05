package ey.samarin.models


data class StockPreview(
    val symbol: String,
    val longName: String,
)


val STUB_StockPreview = listOf(
    StockPreview(symbol = "AAPL", longName = "Apple Inc."),
    StockPreview(symbol = "TSLA", longName = "Tesla Inc."),
    StockPreview(symbol = "GOOG", longName = "Alphabet Inc."),
    StockPreview(symbol = "AMZN", longName = "Amazon.com Inc."),
    StockPreview(symbol = "MSFT", longName = "Microsoft Corporation"),
    StockPreview(symbol = "FB", longName = "Facebook Inc."),
    StockPreview(symbol = "NVDA", longName = "NVIDIA Corporation"),
    StockPreview(symbol = "PYPL", longName = "PayPal Holdings Inc."),
    StockPreview(symbol = "INTC", longName = "Intel Corporation"),
)
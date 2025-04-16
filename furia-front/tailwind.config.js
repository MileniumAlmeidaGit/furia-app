/** @type {import("tailwindcss").Config} */
module.exports = {
    content: ["./src/**/*.{js,jsx,ts,tsx}"],  // olha o caminho certinho
    theme: {
        extend: {
            colors: {
                furia: {
                    orange: "#FF6600",
                    black:  "#0A0A0A",
                    gray:   "#2C2C2C",
                    white:  "#FFFFFF",
                },
            },
        },
    },
    plugins: [],
};

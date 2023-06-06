import hashlib

# Set the values of g, p, x, and y
g = 5  # Primitive root
p = 23  # Prime number
x = 6  # Alice's private key
y = 15  # Bob's private key

# Generate public keys for Alice and Bob
pub_key_a = pow(g, x, p)
pub_key_b = pow(g, y, p)

# Generate shared secret key
shared_secret_a = pow(pub_key_b, x, p)
shared_secret_b = pow(pub_key_a, y, p)

# Derive symmetric key from shared secret
key = hashlib.sha256(str(shared_secret_a).encode()).digest()

# Print symmetric key
print("Symmetric Key:", key.hex())

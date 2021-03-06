import numpy as np
from sklearn.svm import SVC

# win rate
# x_train = [1, 1, 1, 0.333333333333333, 1, 1, 1, 0, 0.5, 1,  
# 		   1, 1, 0.25, 1, 1, 0.142857142857143, 0.2, 1, 1, 0,
# 		   1, 1, 0.5, 1, 1, 0, 1, 0, 0, 0.5,
# 		   1, 1, 0, 0.5, 1, 0, 0, 0, 1, 0,
# 		   0.5, 1, 0.333333333333333, 0.5, 0.333333333333333, 1, 0.5, 0.142857142857143, 1, 0.5,
# 		   1, 1, 0.043478260869565, 1, 1, 0.166666666666667, 0.5, 1, 0.5, 1,
# 		   1, 1, 0, 1, 1, 0.5, 0.5, 0.2, 1, 1,
# 		   1, 1, 0.333333333333333, 0.5, 1, 0.142857142857143, 0.5, 0.25, 1, 1,
# 		   1, 1, 0.5, 0.25, 1, 0.5, 0.25, 0.041666666666667, 1, 0.333333333333333, 
# 		   1, 1, 1, 1, 1, 1, 1, 0.166666666666667, 1, 1,
# 		   1, 1, 0.166666666666667, 0.333333333333333, 1, 1, 0.5, 0, 1, 1,
# 		   1, 1, 0.142857142857143, 1, 0.5, 0.1, 1, 0, 1, 0.5,
# 		   1, 0.333333333333333, 0, 0.333333333333333, 1, 0.5, 0.333333333333333, 0, 1, 1
# 		   ]


x_train = [1, 1, 1, 0.33, 1, 1, 1, 0, 0.5, 1,  
		   1, 1, 0.25, 1, 1, 0.14, 0.2, 1, 1, 0,
		   1, 1, 0.5, 1, 1, 0, 1, 0, 0, 0.5,
		   1, 1, 0, 0.5, 1, 0, 0, 0, 1, 0,
		   0.5, 1, 0.33, 0.5, 0.33, 1, 0.5, 0.14, 1, 0.5,
		   1, 1, 0.43, 1, 1, 0.17, 0.5, 1, 0.5, 1,
		   1, 1, 0, 1, 1, 0.5, 0.5, 0.2, 1, 1,
		   1, 1, 0.33, 0.5, 1, 0.14, 0.5, 0.25, 1, 1,
		   1, 1, 0.5, 0.25, 1, 0.5, 0.25, 0.04, 1, 0.33, 
		   1, 1, 1, 1, 1, 1, 1, 0.17, 1, 1,
		   1, 1, 0.17, 0.33, 1, 1, 0.5, 0, 1, 1,
		   1, 1, 0.14, 1, 0.5, 0.1, 1, 0, 1, 0.5,
		   1, 0.33, 0, 0.33, 1, 0.5, 0.33, 0, 1, 1
		   ]
# label
y_train = [1, 1, 1, 2, 1, 2, 1, 3, 1, 2,
		   2, 1, 3, 2, 2, 3, 3, 2, 1, 3,
		   2, 2, 3,	2, 2, 3, 2,	3, 3, 2,
		   1, 1, 3,	2, 1, 3, 3,	3, 1, 2, 
		   1, 1, 2, 1, 2, 2, 1, 2, 3, 1, 
		   1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 
		   1, 1, 1, 3, 1, 1, 2, 3, 1, 1, 
		   1, 1, 2, 1, 1, 3, 1, 3, 1, 1,  
		   1, 1, 2, 2, 1, 1, 2, 3, 1, 2, 
		   1, 1, 1, 1, 1, 1, 1, 3, 1, 1,
		   1, 2, 3, 2, 1, 1, 1, 3, 1, 1,
		   1, 1, 3, 1, 2, 3, 1, 3, 1, 2,
		   1, 2, 3, 2, 1, 1, 2, 3, 1, 1
		   ]

x_train = np.asarray(x_train)
x_train = x_train.reshape((-1,1))

y_train = np.asarray(y_train)
y_train = y_train.reshape((-1,1))

clf = SVC()
clf.fit(x_train, y_train)

z_test = [0.47, 0.56, 0.22, 0.85, 0.68]
z_test = np.asarray(z_test)
z_test = z_test.reshape((-1,1))

# 100 times: 0.99, 1, 0.36, 0.73, 0.72, 0.46, 0.5, 0.01, 0.85, 0.57
# x_test = [0.46, 0.5, 0.01, 0.85, 0.57]
x_test = [0.32, 0.39, 0.02, 0.65, 0.35]
# 10 times: 1, 1, 0.4, 0.8, 0.8, 0.5, 0.4, 0.1, 0.7, 0.4
# x_test = [0.5, 0.4, 0.1, 0.7, 0.4]
x_test = np.asarray(x_test)
x_test = x_test.reshape((-1,1))

print(clf.predict(z_test))
print(clf.predict(x_test))
# [1 1 2 1 1 2 2 3 1 2]
# [1 1 2 1 1 2 2 3 1 2]


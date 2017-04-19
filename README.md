
## EventView 

EventView - push event of state app.

Simple library to get events on change status Activity for Custom View.

## Download

Download via Gradle:

compile 
or Maven:

<dependency>
  <groupId></groupId>
  <artifactId></artifactId>
  <version>0.1</version>
  <type>pom</type>
</dependency>

## Usage

Create your custom view, and add StateView:

public class CustomFrameLayout extends FrameLayout implements StateView.StateEventListener {
    private final static String TAG = "CustomFrameLayout";

    public CustomFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        StateView stateView = new StateView(context);
        stateView.setStateEventListener(this);
        addView(stateView);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }
}

## License

Copyright 2016 yurasik

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


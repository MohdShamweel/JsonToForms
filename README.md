# JsonToForms

A simple library which uses JSON to build forms

![1](https://user-images.githubusercontent.com/34341190/69613408-881ca800-1057-11ea-83ad-57a245396b04.jpeg)
<img src="https://user-images.githubusercontent.com/34341190/69613408-881ca800-1057-11ea-83ad-57a245396b04.jpeg" style="width: 50%; height: 50%">
![2](https://user-images.githubusercontent.com/34341190/69613430-92d73d00-1057-11ea-880a-74f22b4e887d.jpeg)

## Installation

Step 1. Add the JitPack repository to your build file:

```bash
repositories {
	...
	maven { url 'https://jitpack.io' }
	}

```
  
Step 2. Add the dependency


```bash
dependencies {
	  implementation 'com.github.MohdShamweel:JsonToForms:0.1.0'
                
     //Support Material dependency
     implementation 'com.google.android.material:material:1.1.0-alpha09'
	     }
	
```

Step 3. The dependency contains Java 8 bytecode. Add the following to build.gradle
```bash
           android {
               compileOptions {
                   sourceCompatibility 1.8
                   targetCompatibility 1.8
               }
           }
```

Step 4. The Activity which contains forms must be a descendant of Material Theme:

```bash
parent="Theme.MaterialComponents.Light.DarkActionBar"
``` 
Step 5. Sync the Project for gradle to finish.	

## Usage

1. Initialize the Hashmap (basically it clears the list):
```bash
DataValueHashMap.init();
```
2. Use the adapter and Object of this library:
```bash
FormAdapter mAdapter;
List<JSONModel> jsonModelList = new ArrayList<>();
```

3. Initialize the adapter:
```bash
mAdapter = new FormAdapter(jsonModelList, this, this);
```

4. Implement your Activity to access methods
```
implements JsonToFormClickListener
```
5. Implemented Methods
```bash
 @Override
    public void onAddAgainButtonClick() {
        Toast.makeText(this, "Add Again button click", Toast.LENGTH_SHORT).show();
    }

 @Override
    public void onSubmitButtonClick() {
        Toast.makeText(this, "Add Submit button click", Toast.LENGTH_SHORT).show();
    }
```
6. To check if fields are validated use the method:
```bash
if (!CheckFieldValidations.isFieldsValidated(recyclerView, jsonModelList)){
            Toast.makeText(this, "Validation Failed", Toast.LENGTH_SHORT).show();
    }
```

## Fields Types
```
TYPE_TEXT = 1;
TYPE_EDITTEXT = 2;
TYPE_SPINNER = 3;
TYPE_RADIO = 4;
TYPE_DATE = 5;
TYPE_SPACE = 6;
TYPE_CHECKBOX = 7;
TYPE_ADD_AGAIN_BUTTON = 9;
TYPE_SUBMIT_BUTTON = 10;
```

## Sample JSON

```bash
[
  {
    "_id": "text_1",
    "text": "Hi! I'm textview",
    "hint": "Hi! I'm textview",
    "type": 1
  },
  {
    "_id": "edittext_1",
    "text": "Hi! I'm edittext(Enter Numbers)",
    "hint": "Hi! I'm edittext(Enter Numbers)",
    "input_type": "numbers",
    "max_length": 2,
    "type": 2
  },
  {
    "_id": "edittext_2",
    "text": "Hi! I'm edittext(Enter Numbers Decimal)",
    "hint": "Hi! I'm edittext(Enter Numbers Decimal)",
    "input_type": "numbers_decimal",
    "max_length": 7,
    "type": 2
  },
  {
    "_id": "edittext_3",
    "text": "Hi! I'm edittext(Enter text) *Required",
    "hint": "Hi! I'm edittext(Enter text)",
    "input_type": "text",
    "is_required": true,
    "max_length": 30,
    "type": 2
  },
  {
    "_id": "radio_group_1",
    "text": "Hi! I'm radio group",
    "is_required": true,
    "list": [
      {
        "index": 0,
        "index_text": "Radio 1"
      },
      {
        "index": 1,
        "index_text": "Radio 2"
      },
      {
        "index": 2,
        "index_text": "Radio 3"
      },
      {
        "index": 3,
        "index_text": "Radio 4"
      }
    ],
    "type": 4
  },
  {
    "_id": "spinner_1",
    "text": "Hi! I'm spinner",
    "list": [
      {
        "index": 0,
        "index_text": "Item 1"
      },
      {
        "index": 1,
        "index_text": "Item 2"
      },
      {
        "index": 2,
        "index_text": "Item 3"
      },
      {
        "index": 3,
        "index_text": "Item 4"
      }
    ],
    "type": 3
  },
  {
    "_id": "again_button",
    "text": "Hi! I'm add button",
    "type": 9
  },
  {
    "_id": "space_1",
    "text": "Hi, I am Space",
    "type": 6
  },
  {
    "_id": "submit_button",
    "text": "Submit",
    "type": 10
  }
]
```

## Example:
To know more about implementation please checkout the [Sample App](https://github.com/MohdShamweel/JsonToForms/tree/master/app)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

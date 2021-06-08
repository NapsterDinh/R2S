<?php
namespace App\Services;

use App\Models\AssignmentRequest;
use App\Models\Classs;
use Illuminate\Support\Facades\DB;

class ClassService {
    public function __construct() {

    }

    public function showClass($isAll) {
        if($isAll=='true')
        {
            $class = DB::table('Class')->select(
                'ClassID as classID',
                'ClassName as className',
                'Capacity as capacity',
                'StartTime as startTime',
                'EndTime as endTime',
                'IsDeleted as isDeleted'
            )
            ->get();
    
            return json_encode($class);
        }
        else
        {
            $class = DB::table('Class')->select(
                'ClassID as classID',
                'ClassName as className',
                'Capacity as capacity',
                'StartTime as startTime',
                'EndTime as endTime',
                'IsDeleted as isDeleted'
            )
            ->where('IsDeleted', '=', 0)
            ->get();
    
            return json_encode($class);
        }
        
    }

    public function getAll() {
        $class = DB::table('Class')->select(
            'ClassID as classID',
            'ClassName as className',
            'Capacity as capacity',
            'StartTime as startTime',
            'EndTime as endTime',
            'IsDeleted as isDeleted'
        )->where('IsDeleted','=','0')
        ->get();

        return json_encode($class);
    }

    public function insertClass($data) {
        $duplicateItem = DB::table('Class')->where('ClassName', $data['className'])->get()->toArray();
        if (count($duplicateItem) > 0) {
            return [
                'message' => 'Duplicated'
            ];
        }

        $class = Classs::create([
            'ClassID' => rand(1,1000),
            'ClassName' => $data['className'],
            'Capacity' => $data['capacity'],
            'StartTime' => date("Y-m-d",strtotime($data['startTime'])),
            'EndTime' => date("Y-m-d",strtotime($data['endTime'])),
            'IsDeleted' => 0,
        ]);

        return json_encode([
            'message' => 'Done'
        ]);
    }

    public function editClass($data) {
        $class = DB::table('Class')->where('ClassID', $data['classID'])
        ->update([
            'ClassName' => $data['className'],
            'Capacity' => $data['capacity'],
            'StartTime' => date("Y-m-d",strtotime($data['startTime'])),
            'EndTime' => date("Y-m-d",strtotime($data['endTime'])),
            'IsDeleted' => 0,
        ]); 

        return json_encode([
            "result" => $class
        ]);
    }

    public function deleteClass($data) {
        $class = DB::table('Class')->where('ClassID', $data['Id'])
                    ->update([
                        'IsDeleted' => 1,
                    ]);

        return json_encode([
            "result" => $class
        ]);
    }
}
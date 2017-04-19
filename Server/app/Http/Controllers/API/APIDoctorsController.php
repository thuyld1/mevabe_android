<?php

namespace App\Http\Controllers\API;

use App\Http\Requests;

use App\Models\Doctor;
use App\Models\LocationDistrict;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Config;
use Session;

class APIDoctorsController extends APIController
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function findDoctor(Request $request)
    {
        $perPage = Config::get('constant.API_RECORD_PER_PAGE');
        $result = Doctor::select('name', 'avatar', 'phone', 'des', 'vote', 'province', 'district', 'specialization')
            ->where('status', '=', 0)
            ->paginate($perPage);
        return $result;
    }


    /**
     * Get list districts by province
     *
     * @param \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function getDistricts(Request $request)
    {
        $province = $request->get('province');
        $list = $this->getDistrict($province);
        return $list;
    }

    /**
     * Get all province data for selection box
     * @param int $province
     * @return array
     */
    private function getDistrict($province = null)
    {
        if (empty($province)) {
            $districts = array();
        } else {
            $districts = LocationDistrict::select('code', 'title')
                ->where('p_code', '=', $province)
                ->pluck('title', 'code')
                ->toArray();
        }
        return $districts;
    }

}
